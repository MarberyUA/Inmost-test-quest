package com.dev.testquest.service.impl;

import com.dev.testquest.dao.UserDao;
import com.dev.testquest.model.Role;
import com.dev.testquest.model.User;
import com.dev.testquest.service.RoleService;
import com.dev.testquest.service.UserService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Override
    public User findByEmail(String userEmail) {
        return userDao.findByEmail(userEmail);
    }

    @Override
    public User create(User user) {
        Role role = roleService.getRoleByName("USER");
        user.setRoles(Set.of(role));
        return userDao.create(user);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
