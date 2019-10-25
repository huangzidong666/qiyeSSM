package com.huangjindong.service.impl;

import com.huangjindong.dao.IRolesDao;
import com.huangjindong.domian.Permission;
import com.huangjindong.domian.Role;
import com.huangjindong.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRolesDao iRolesDao;
    @Override
    public List<Role> findAll() {
        return iRolesDao.findAll();
    }

    @Override
    public void addRole(Role role) {
        iRolesDao.addRole(role);
    }

    @Override
    public Role findById(String roleId) {
        return iRolesDao.findByRoleId(roleId);
    }

    @Override
    public List<Permission> findRoleByIdAndAllPermission(String roleId) {
        return iRolesDao.findRoleByIdAndAllPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId:permissionIds) {
            iRolesDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
