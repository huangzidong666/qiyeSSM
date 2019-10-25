package com.huangjindong.service;

import com.huangjindong.domian.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll();

    void addPermission(Permission permission);
}
