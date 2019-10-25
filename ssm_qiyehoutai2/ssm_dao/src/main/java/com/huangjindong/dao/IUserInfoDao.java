package com.huangjindong.dao;

import com.huangjindong.domian.Role;
import com.huangjindong.domian.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserInfoDao {
    @Select("select * from Users where username=#{username}")
    @Results({
            @Result(id = true,column ="id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),

            @Result(column = "id",property = "roles",javaType = java.util.List.class,many = @Many(select = "com.huangjindong.dao.IRolesDao.findById"))
    })
    UserInfo findByName(String name);

    @Select("select * from Users")
    List<UserInfo> findAll();
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void addUserInfo(UserInfo userInfo);


    @Select("select * from users where id =#{id}")
    @Results({
            @Result(id = true,column ="id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),

            @Result(column = "id",property = "roles",javaType = java.util.List.class,many = @Many(select = "com.huangjindong.dao.IRolesDao.findById"))
    })
    UserInfo findById(String id);
    @Select("select * from role where id not in (select roleid from users_role where userid=#{userid})")
    List<Role> findUserByIdAddAllRole(String userid);

    @Insert("insert into users_role(userId,roleId)values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
