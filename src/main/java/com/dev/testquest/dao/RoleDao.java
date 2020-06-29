package com.dev.testquest.dao;

import com.dev.testquest.model.Role;

public interface RoleDao {
    Role add(Role role);

    Role getRoleByName(String roleName);
}
