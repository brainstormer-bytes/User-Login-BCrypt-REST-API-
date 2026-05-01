package com.projectmine.ECommerce.Web.controller;

import com.projectmine.ECommerce.Web.model.User;
import com.projectmine.ECommerce.Web.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    LoginService service;
    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> handleForm(@RequestBody User u, HttpSession session) {
        System.out.println(u.toString());
        String email = u.getEmail();
        String password = u.getPassword();
        if(service.authenticate(email,password)) {
            User u1 = service.getUser(email); //it returns name, email, password
            session.setAttribute("userName", u1.getName());
            session.setAttribute("userEmail", u1.getEmail());
            return ResponseEntity.ok(Map.of("success",true,"name",u1.getName()));
        } else {
            return ResponseEntity
                    .status(401)
                    .body(Map.of("success",false,"message","Invalid credentails"));
        }
    }
}
