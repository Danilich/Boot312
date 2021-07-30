package com.example.Boot311.dao;



import com.example.Boot311.models.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRoles();

    Role getRole(long id);
}
