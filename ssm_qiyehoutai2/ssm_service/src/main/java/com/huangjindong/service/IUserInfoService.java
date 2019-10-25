package com.huangjindong.service;

import com.huangjindong.domian.Role;
import com.huangjindong.domian.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserInfoService extends UserDetailsService {
    List<UserInfo> findAll();
    void addUserInfo(UserInfo userInfo);
    UserInfo findById(String id);

    List<Role> findUserByIdAddAllRole(String userid);

    void addRoleToUser(String userId, String[] roleIds);
}
