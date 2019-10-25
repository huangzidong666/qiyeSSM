package com.huangjindong.service.impl;

import com.huangjindong.dao.IUserInfoDao;
import com.huangjindong.domian.Role;
import com.huangjindong.domian.UserInfo;
import com.huangjindong.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserInfoService implements IUserInfoService {
    @Autowired
    private IUserInfoDao iUserInfoDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = iUserInfoDao.findByName(username);
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list =new ArrayList<SimpleGrantedAuthority>();
        for (Role role:roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return  list;
    }

    @Override
    public List<UserInfo> findAll() {

        return iUserInfoDao.findAll();
    }

    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    @Override
    public void addUserInfo(UserInfo userInfo) {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        iUserInfoDao.addUserInfo(userInfo);
    }

    @Override
    public UserInfo findById(String id) {

        return iUserInfoDao.findById(id);
    }

    @Override
    public List<Role> findUserByIdAddAllRole(String userid) {
        return iUserInfoDao.findUserByIdAddAllRole(userid);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId:roleIds) {
            iUserInfoDao.addRoleToUser(userId,roleId);
        }
    }
}
