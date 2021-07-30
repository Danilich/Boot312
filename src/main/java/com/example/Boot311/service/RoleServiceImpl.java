package com.example.Boot311.service;


import com.example.Boot311.dao.RoleDao;
import com.example.Boot311.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public Role getRoleById(long id) {
        return roleDao.getRole(id);
    }
}
