package com.dev.testquest.controller;

import com.dev.testquest.model.User;
import com.dev.testquest.model.dto.request.AuthenticationLoginRequestDto;
import com.dev.testquest.model.dto.request.AuthenticationRegisterRequestDto;
import com.dev.testquest.model.mapper.AuthenticationMapper;
import com.dev.testquest.security.jwt.JwtTokenProvider;
import com.dev.testquest.service.AuthenticationService;
import com.dev.testquest.service.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationMapper authenticationMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationLoginRequestDto
                                            authenticationLoginRequestDto) {

        try {
            String userEmail = authenticationLoginRequestDto.getUserEmail();
            authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(userEmail,
                    authenticationLoginRequestDto.getPassword()));
            User user = userService.findByEmail(userEmail);
            if (user == null) {
                throw new UsernameNotFoundException("User not found!");
            }
            String token = jwtTokenProvider.createToken(userEmail, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("useremail", userEmail);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid data!");
        }
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid AuthenticationRegisterRequestDto
                                     authenticationRequestDto)
            throws AuthenticationException {
        User user = authenticationMapper.authenticationRequestDtoToUser(authenticationRequestDto);
        authenticationService.registration(user.getName(), user.getEmail(),
                user.getPassword());
    }
}
