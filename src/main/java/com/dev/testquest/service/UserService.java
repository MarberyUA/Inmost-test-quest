package com.dev.testquest.service;

import com.dev.testquest.model.User;
import java.util.List;

public interface UserService {
    User findByEmail(String userEmail);

    User create(User user);

    User update(User user);

    void delete(User user);

    List<User> getAll();

    List<User> paginatedUserList(Integer page);
}
