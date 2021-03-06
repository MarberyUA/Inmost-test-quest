package com.dev.testquest.service.impl;

import com.dev.testquest.model.User;
import com.dev.testquest.service.AuthenticationService;
import com.dev.testquest.service.RoleService;
import com.dev.testquest.service.UserService;
import java.time.LocalDate;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public User registration(String name, String email, String password)
            throws AuthenticationException {
        User user = new User();
        user.setDateCreation(LocalDate.now());
        user.setLastPasswordResetDate(LocalDate.now());
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userService.create(user);
    }
}
