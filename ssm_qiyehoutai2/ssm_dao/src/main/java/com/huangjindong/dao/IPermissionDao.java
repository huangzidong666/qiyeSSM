package com.huangjindong.dao;

import com.huangjindong.domian.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {
    @Select("select * from permission where id in(select permissionid from role_permission where roleid=#{id})")
    List<Permission> finByRoleId(String id);
    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into Permission(permissionName,url)values(#{permissionName},#{url})")
    void addPermission(Permission permission);
}
