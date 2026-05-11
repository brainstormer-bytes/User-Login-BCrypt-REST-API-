package com.projectmine.ECommerce.Web.controller;

import com.projectmine.ECommerce.Web.model.User;
import com.projectmine.ECommerce.Web.service.RegisterService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegisterController {

    // Constructor Injection (to avoid BeanCreationException)
    RegisterService service;
    public RegisterController(RegisterService service) {
        this.service = service;
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> handleForm(@RequestBody User u) {
        System.out.println(u.toString());
        String result = service.setUser(u);
        if(result.equals("SUCCESS")) {
            return ResponseEntity.ok(Map.of("success", true, "message", "Registration Successful"));
        }
        else if(result.equals("EMAIL_ALREADY_REGISTERED")) {
            return ResponseEntity
                    .status(400)
                    .body(Map.of("success", false, "message", "Email already registered"));
        }
        else if(result.equals("INVALID_DATA")) {
            return ResponseEntity
                    .status(400)
                    .body(Map.of("success", false, "message", "Invalid name, email or password"));
        }
        else {
                return ResponseEntity
                        .status(500)
                        .body(Map.of("success",false,"message","Something went wrong"));
        }
    }

}
