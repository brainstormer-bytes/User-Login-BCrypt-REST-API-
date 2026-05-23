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
public class RegisterController {

<<<<<<< HEAD
    private final RegisterService service;
    private final AuthSessionService authSessionService;

    public RegisterController(RegisterService service, AuthSessionService authSessionService) {
=======
    // Constructor Injection (to avoid BeanCreationException)
    RegisterService service;
    public RegisterController(RegisterService service) {
>>>>>>> 6f559906e5d255d93fceea67d1e97627584e95d1
        this.service = service;
        this.authSessionService = authSessionService;
    }

    @PostMapping("/user/register")
<<<<<<< HEAD
    public ResponseEntity<?> handleForm(@RequestBody User u, HttpServletRequest request) {
        authSessionService.retireCurrentSession(request);
        boolean success = service.setUser(u);
        if (!success) {
            return ResponseEntity
                    .status(400)
                    .body(Map.of("success", false, "message", "Email already registered"));
=======
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
>>>>>>> 6f559906e5d255d93fceea67d1e97627584e95d1
        }

        return ResponseEntity.ok(Map.of("success", true, "message", "Registration successful"));
    }

}
