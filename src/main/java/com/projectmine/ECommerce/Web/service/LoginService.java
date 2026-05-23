package com.projectmine.ECommerce.Web.service;

import com.projectmine.ECommerce.Web.model.User;
import com.projectmine.ECommerce.Web.repository.LoginRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
<<<<<<< HEAD

    private final LoginRepository repo;
    private final PasswordEncoder passwordEncoder;

    public LoginService(LoginRepository repo, PasswordEncoder passwordEncoder) {
=======
    static final BCryptPasswordEncoder hashPass = new BCryptPasswordEncoder(10);

    LoginRepository repo;
    public LoginService(LoginRepository repo){
>>>>>>> 6f559906e5d255d93fceea67d1e97627584e95d1
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticate(String email, String password) {
<<<<<<< HEAD
        User user = repo.getUserDetails(email);
        return user != null
                && user.getEmail() != null
                && user.getPassword() != null
                && email.equalsIgnoreCase(user.getEmail())
                && passwordEncoder.matches(password, user.getPassword());
=======
        User u = repo.getUserDetails(email);
        boolean isPass = hashPass.matches(password, u.getPassword());
        if(isPass && email.equals(u.getEmail())) {
            return true;
        } else {
            return false;
        }
>>>>>>> 6f559906e5d255d93fceea67d1e97627584e95d1
    }

    public User getUser(String email) {
        return repo.getUserDetails(email);
    }
}
