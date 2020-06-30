package com.dev.testquest.model.mapper;

import com.dev.testquest.model.User;
import com.dev.testquest.model.dto.request.AuthenticationRegisterRequestDto;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {
    public User authenticationRequestDtoToUser(AuthenticationRegisterRequestDto
                                                       authenticationRequestDto) {
        User user = new User();
        user.setEmail(authenticationRequestDto.getEmail());
        user.setName(authenticationRequestDto.getName());
        user.setPassword(authenticationRequestDto.getPassword());
        return user;
    }
}
