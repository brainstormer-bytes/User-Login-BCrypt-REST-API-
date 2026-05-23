package com.projectmine.ECommerce.Web.service;

import com.projectmine.ECommerce.Web.model.User;
import com.projectmine.ECommerce.Web.repository.RegisterRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final RegisterRepository repo;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(RegisterRepository repo, PasswordEncoder passwordEncoder){
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    //We have two ways to check this:
    //1. Check karna ki kahi same name,email, ya password to database me already hai to nahi
    //2. Check karna ki kahi session.getAttribute to null nahi hai

    //I will prefer 1.
     public boolean setUser(User u){
         if (repo.IsExists(u)) {
             return false;
         }

         u.setPassword(passwordEncoder.encode(u.getPassword()));
         repo.addUser(u);
         return true;
     }


}
