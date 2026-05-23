package com.projectmine.ECommerce.Web.controller;

import com.projectmine.ECommerce.Web.model.User;
import com.projectmine.ECommerce.Web.service.AuthSessionService;
import com.projectmine.ECommerce.Web.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final LoginService service;
    private final AuthSessionService authSessionService;

    public LoginController(LoginService service, AuthSessionService authSessionService) {
        this.service = service;
        this.authSessionService = authSessionService;
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> handleForm(@RequestBody User u, HttpServletRequest request) {
        String email = u.getEmail();
        String password = u.getPassword();
        if (service.authenticate(email, password)) {
            User authenticatedUser = service.getUser(email);
            authSessionService.startFreshSession(request, authenticatedUser);
            return ResponseEntity.ok(Map.of("success", true, "name", authenticatedUser.getName()));
        }

        authSessionService.retireCurrentSession(request);
        return ResponseEntity
                .status(401)
                .body(Map.of("success", false, "message", "Invalid credentials"));
    }
}
