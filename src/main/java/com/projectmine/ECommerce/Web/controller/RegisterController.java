package com.projectmine.ECommerce.Web.controller;

import com.projectmine.ECommerce.Web.model.User;
import com.projectmine.ECommerce.Web.service.AuthSessionService;
import com.projectmine.ECommerce.Web.service.RegisterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegisterController{

    private final RegisterService service;
    private final AuthSessionService authSessionService;

    public RegisterController(RegisterService service, AuthSessionService authSessionService) {
        this.service = service;
        this.authSessionService = authSessionService;
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> handleForm(@RequestBody User u, HttpServletRequest request) {
        authSessionService.retireCurrentSession(request);
        boolean success = service.setUser(u);
        if (!success) {
            return ResponseEntity
                    .status(400)
                    .body(Map.of("success", false, "message", "Email already registered"));
        }

        return ResponseEntity.ok(Map.of("success", true, "message", "Registration successful"));
    }

}
