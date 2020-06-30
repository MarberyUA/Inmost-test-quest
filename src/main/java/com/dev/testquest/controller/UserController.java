package com.dev.testquest.controller;

import com.dev.testquest.model.Role;
import com.dev.testquest.model.User;
import com.dev.testquest.model.dto.request.UserUpdateRequestDto;
import com.dev.testquest.model.dto.response.UserDetailsResponseDto;
import com.dev.testquest.model.mapper.UserMapper;
import com.dev.testquest.service.RoleService;
import com.dev.testquest.service.UserService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void init() {
        for (Role.RoleName roleName : Role.RoleName.values()) {
            Role role = new Role();
            role.setRoleName(roleName);
            roleService.add(role);
        }
    }

    @PostMapping("/update")
    public UserDetailsResponseDto update(@RequestBody @Valid UserUpdateRequestDto requestDto,
                                         Authentication authentication) {
        User userToBeUpdated = userMapper.userUpdateRequestDtoToUser(requestDto);
        User user = userService.findByEmail(authentication.getName());
        if (userToBeUpdated.getEmail() != null) {
            user.setEmail(userToBeUpdated.getEmail());
        }
        if (userToBeUpdated.getName() != null) {
            user.setName(userToBeUpdated.getName());
        }
        if (userToBeUpdated.getSurname() != null) {
            user.setSurname(userToBeUpdated.getSurname());
        }
        if (userToBeUpdated.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userToBeUpdated.getPassword()));
            user.setLastPasswordResetDate(LocalDate.now());
        }
        userService.update(user);
        return userMapper.userToUserDetailsResponseDto(user);
    }

    @PostMapping("/delete")
    public ResponseEntity delete(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        userService.delete(user);
        Map<Object, Object> response = new HashMap<>();
        response.put("userEmail", user.getEmail());
        response.put("status", "deleted");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/details")
    public UserDetailsResponseDto details(Authentication authentication) {
        return userMapper.userToUserDetailsResponseDto(userService
                .findByEmail(authentication.getName()));
    }

    @GetMapping
    public List<UserDetailsResponseDto> getAllUsers(@RequestParam @Valid Integer page) {
        List<User> users = userService.paginatedUserList(page);
        List<UserDetailsResponseDto> responseDtos = new ArrayList<>();
        for (User user : users) {
            responseDtos.add(userMapper.userToUserDetailsResponseDto(user));
        }
        return responseDtos;
    }
}
