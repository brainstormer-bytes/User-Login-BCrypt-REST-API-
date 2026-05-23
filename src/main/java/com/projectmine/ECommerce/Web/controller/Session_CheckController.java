package com.projectmine.ECommerce.Web.controller;

import com.projectmine.ECommerce.Web.service.AuthSessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class Session_CheckController {

    private final AuthSessionService authSessionService;

    public Session_CheckController(AuthSessionService authSessionService) {
        this.authSessionService = authSessionService;
    }

    @GetMapping("/session-check")
    public ResponseEntity<?> sessionCheck(HttpSession session) {
       String name = authSessionService.getUserName(session);
       String email = authSessionService.getUserEmail(session);
       if (name == null || email == null) {
           return ResponseEntity
                   .status(401)
                   .body(Map.of("loggedIn", false));
       }

       return ResponseEntity.ok(Map.of("loggedIn", true, "name", name, "email", email));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        authSessionService.retireCurrentSession(request);
        return ResponseEntity.ok(Map.of("success", true));
    }
}
