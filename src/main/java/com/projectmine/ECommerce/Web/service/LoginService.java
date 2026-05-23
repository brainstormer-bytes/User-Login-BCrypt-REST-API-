package com.projectmine.ECommerce.Web.service;

import com.projectmine.ECommerce.Web.model.User;
import com.projectmine.ECommerce.Web.repository.LoginRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginRepository repo;
    private final PasswordEncoder passwordEncoder;

    public LoginService(LoginRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticate(String email, String password) {
        User user = repo.getUserDetails(email);
        return user != null
                && user.getEmail() != null
                && user.getPassword() != null
                && email.equalsIgnoreCase(user.getEmail())
                && passwordEncoder.matches(password, user.getPassword());
    }

    public User getUser(String email) {
        return repo.getUserDetails(email);
    }
}
