package com.projectmine.ECommerce.Web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class Session_CheckController {

    @RequestMapping("/session-check")
    public ResponseEntity<?> sessionCheck(HttpSession session) {
       String name = (String) session.getAttribute("userName");
       String email = (String) session.getAttribute("userEmail");
       if(name == null) {
           return ResponseEntity
                   .status(401)
                   .body(Map.of("loggedIn",false));
       } else {
           return ResponseEntity.ok(Map.of("loggedIn",true,
                                           "name",name,
                                           "email", email != null ? email : ""));
       }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of("success",true));
    }
}