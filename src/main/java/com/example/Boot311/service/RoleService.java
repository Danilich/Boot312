package com.example.Boot311.service;



import com.example.Boot311.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(long id);
}
