package com.dev.testquest.security.jwt;

import com.dev.testquest.model.Role;
import com.dev.testquest.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {
    public JwtUserFactory() {

    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getPassword(),
                user.getEmail(),
                true,
                new Date(2020, 6, 30),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
                );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRole) {
        return userRole.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());
    }
}
