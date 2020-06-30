package com.dev.testquest.model.mapper;

import com.dev.testquest.model.User;
import com.dev.testquest.model.dto.request.UserUpdateRequestDto;
import com.dev.testquest.model.dto.response.UserDetailsResponseDto;
import com.dev.testquest.security.jwt.JwtUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDetailsResponseDto userToUserDetailsResponseDto(User user) {
        UserDetailsResponseDto responseDto = new UserDetailsResponseDto();
        responseDto.setId(user.getId());
        responseDto.setFirstName(user.getName());
        responseDto.setLastName(user.getSurname());
        responseDto.setEmail(user.getEmail());
        return responseDto;
    }

    public UserDetailsResponseDto userToUserDetailsResponseDto(JwtUser user) {
        UserDetailsResponseDto responseDto = new UserDetailsResponseDto();
        responseDto.setId(user.getId());
        responseDto.setFirstName(user.getUsername());
        responseDto.setLastName(user.getLastName());
        responseDto.setEmail(user.getEmail());
        return responseDto;
    }

    public User userUpdateRequestDtoToUser(UserUpdateRequestDto requestDto) {
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setName(requestDto.getFirstName());
        user.setSurname(requestDto.getLastName());
        user.setPassword(requestDto.getPassword());
        return user;
    }
}
