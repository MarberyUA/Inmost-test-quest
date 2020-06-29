package com.dev.testquest.service;

import com.dev.testquest.model.User;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationService {
    User registration(String name, String email, String password)
            throws AuthenticationException;
}
