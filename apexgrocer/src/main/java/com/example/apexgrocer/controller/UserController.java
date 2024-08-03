package com.example.apexgrocer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;

import com.example.apexgrocer.entity.AuthRequest;
import com.example.apexgrocer.entity.UserInfo;
import com.example.apexgrocer.model.User;
import com.example.apexgrocer.repository.UserRepository;
import com.example.apexgrocer.service.JwtService;
import com.example.apexgrocer.service.UserInfoService;
import com.example.apexgrocer.service.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.*;
@RestController
@RequestMapping("/request")
@CrossOrigin("http://localhost:5173/")
public class UserController {
    @Autowired 
    //intead of autowired we can also static final import 
    private UserService us;
    @Autowired
    private UserRepository ur;
    @Autowired
    private JwtService jws;
    @Autowired
    private UserInfoService uis;
    @Autowired
    private AuthenticationManager am;
    @GetMapping("/getusers")
    public List<User> getAllUser() {
        return us.getUsers();
    }
    @PostMapping("/register")
    public User addUser(@RequestBody User u)
    {
        return us.addUsers(u);
        
    }
    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> l)
    {
       String email=l.get("email");
       String password=l.get("password");
       User result=ur.findByEmail(email);
       if(result==null)
       {
        return "Invalid User";
       }
       else
       {
        if(result.getPassword().equals(password))
        {
            return "Login successful";
        }
        else
        {

            return "login failed";
        }
       }
    

        
        
    }
    @GetMapping("/getbyemail/{email}")
    public User getById(@PathVariable String email)
    {
        return ur.findByEmail(email);

    }
    
    @DeleteMapping("/deletebyid/{uid}")
    public String delete(@PathVariable Long uid)
    {
        return us.deleteuser(uid);
    }
    @PutMapping("putbyemail/{email}")
    public User putbyemail(@RequestBody User u,@PathVariable String email)
    {
       User up=ur.findByEmail(email);
       if(up!=null)
       {
        up.setUsername(u.getUsername());
        up.setPassword(u.getPassword());
        
       }
       return u;

    }
    @PostMapping("/adduser")
    public String adduser(@RequestBody UserInfo userInfo)
    {
        return uis.addUser(userInfo);
        
    }
    @GetMapping("user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile()
    {
        return "welcome to user profile";
    }
    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile()
    {
        return "welcome to admin profile";
    }
  
    @PostMapping("/generateToken")
    public String authenticateandGetToken(@RequestBody AuthRequest authRequest)
    {
        Authentication authentication=am.authenticate(UsernamePasswordAuthentication(authRequest.getUsername()),authRequest.getPassword());
        if(authentication.isAuthenticated()){
        return 
        jws.generateToken(authRequest.getUsername());
        }
        else{
            throw new UsernameNotFoundException("Invalid user request");
        }
    }
  
    



    
}
