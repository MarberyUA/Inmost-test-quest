package com.dev.testquest.dao;

import com.dev.testquest.model.User;
import java.util.List;

public interface UserDao {
    User create(User user);

    User update(User user);

    List<User> getAll();

    void delete(User user);

    User findByEmail(String email);
}
