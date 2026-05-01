package com.projectmine.ECommerce.Web.service;

import com.projectmine.ECommerce.Web.model.User;
import com.projectmine.ECommerce.Web.repository.RegisterRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    RegisterRepository repo;
    public RegisterService (RegisterRepository repo){
        this.repo = repo;
    }

    //We have two ways to check this:
    //1. Check karna ki kahi same name,email, ya password to database me already hai to nahi
    //2. Check karna ki kahi session.getAttribute to null nahi hai

    //I will prefer 1.
     public boolean setUser(User u){
         if(!repo.IsExists(u)){

             BCryptPasswordEncoder hashPass = new BCryptPasswordEncoder(10);
             u.setPassword(hashPass.encode(u.getPassword()));
             repo.addUser(u);
             return true;
         } else {
             return false;
         }
     }


}