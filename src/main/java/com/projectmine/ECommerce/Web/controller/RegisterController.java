package com.projectmine.ECommerce.Web.controller;

import com.projectmine.ECommerce.Web.model.User;
import com.projectmine.ECommerce.Web.service.RegisterService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegisterController{

    RegisterService service;
    public RegisterController (RegisterService service) {
        this.service = service;
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> handleForm(@RequestBody User u) {
        System.out.println(u.toString());
        boolean success = service.setUser(u);
        if(!success) {
            return ResponseEntity
                    .status(400)
                    .body(Map.of("success",false,"message","Email already registered"));
        } else {
            return ResponseEntity.ok(Map.of("success",true,"message","Registration Successful"));
        }
    }

}
