package com.huangjindong.service;

import com.huangjindong.domian.Permission;
import com.huangjindong.domian.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();

    void addRole(Role role);

    Role findById(String roleId);

    List<Permission> findRoleByIdAndAllPermission(String roleId);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
