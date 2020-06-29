package com.dev.testquest.service;

import com.dev.testquest.model.Role;

public interface RoleService {
    Role add(Role role);

    Role getRoleByName(String roleName);
}
