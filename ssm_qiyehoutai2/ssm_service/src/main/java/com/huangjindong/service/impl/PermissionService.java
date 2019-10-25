package com.huangjindong.service.impl;

import com.huangjindong.dao.IPermissionDao;
import com.huangjindong.domian.Permission;
import com.huangjindong.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionService implements IPermissionService {
    @Autowired
    private IPermissionDao iPermissionDao;

    @Override
    public List<Permission> findAll() {
        return iPermissionDao.findAll();
    }

    @Override
    public void addPermission(Permission permission) {
        iPermissionDao.addPermission(permission);
    }
}
