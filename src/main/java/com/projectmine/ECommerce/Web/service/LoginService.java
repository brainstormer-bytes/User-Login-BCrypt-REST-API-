package com.projectmine.ECommerce.Web.service;

import com.projectmine.ECommerce.Web.model.User;
import com.projectmine.ECommerce.Web.repository.LoginRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {


    LoginRepository repo;
    public LoginService (LoginRepository repo){
        this.repo = repo;
    }


    public boolean authenticate(String email, String password) {
        User u = repo.getUserDetails(email);
        BCryptPasswordEncoder hashPass = new BCryptPasswordEncoder(10);
        boolean isPass = hashPass.matches(password, u.getPassword());
        if(isPass && email.equals(u.getEmail())) {
            return true;
        } else {
            return false;
        }
    }

    public User getUser(String email) {
        return repo.getUserDetails(email);
    }
}
