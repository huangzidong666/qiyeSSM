package com.huangjindong.dao;

import com.huangjindong.domian.Permission;
import com.huangjindong.domian.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRolesDao {
    @Select("select * from Role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property ="id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.huangjindong.dao.IPermissionDao.finByRoleId"))
    })
    List<Role> findById(String userId);

    @Select("select * from Role")
    List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc)values(#{roleName},#{roleDesc})")
    void addRole(Role role);
    @Select("select * from Role where id=#{roleId}")
    Role findByRoleId(String roleId);

    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findRoleByIdAndAllPermission(String roleId);

    @Insert("insert into Role_permission(permissionId,roleId)values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
