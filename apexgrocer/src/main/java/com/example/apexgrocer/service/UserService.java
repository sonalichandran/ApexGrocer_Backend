package com.example.apexgrocer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apexgrocer.model.User;
import com.example.apexgrocer.repository.UserRepository;
import java.util.*;

@Service
public class UserService {
    @Autowired
    UserRepository ur;
    public List<User> getUsers(){
        return ur.findAll();
        //findAll() will execute select * from users;


    }
    public User addUsers(User user)

    {
        return ur.save(user);
    }
    public String deleteuser(Long uid)
    {
        ur.deleteById(uid);
        return "user deleted successfully"+ uid;
    }
    
}
