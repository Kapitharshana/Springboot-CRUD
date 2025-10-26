package com.example.demo.service;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userrepo;

   public User craeteuser(User user){
       return userrepo.save(user);
   }

   public User getbyid(Long id){
       return userrepo.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
   }





   }
