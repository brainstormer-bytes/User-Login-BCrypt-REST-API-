package com.projectmine.ECommerce.Web.service;

import com.projectmine.ECommerce.Web.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class AuthSessionService {

    private static final String USER_NAME = "userName";
    private static final String USER_EMAIL = "userEmail";

    public void retireCurrentSession(HttpServletRequest request) {
        HttpSession existingSession = request.getSession(false);
        if (existingSession != null) {
            existingSession.invalidate();
        }
    }

    public void startFreshSession(HttpServletRequest request, User user) {
        retireCurrentSession(request);

        HttpSession newSession = request.getSession(true);
        newSession.setAttribute(USER_NAME, user.getName());
        newSession.setAttribute(USER_EMAIL, user.getEmail());
    }

    public String getUserName(HttpSession session) {
        return (String) session.getAttribute(USER_NAME);
    }

    public String getUserEmail(HttpSession session) {
        return (String) session.getAttribute(USER_EMAIL);
    }
}
