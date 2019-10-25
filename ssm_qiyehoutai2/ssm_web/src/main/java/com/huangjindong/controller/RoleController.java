package com.huangjindong.controller;

import com.huangjindong.domian.Permission;
import com.huangjindong.domian.Role;
import com.huangjindong.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        List<Role> roleList = iRoleService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }
    @RequestMapping("/addRole.do")
    public String addRole(Role role){
        iRoleService.addRole(role);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true)String roleId){
        Role role = iRoleService.findById(roleId);
        List<Permission> permissionList = iRoleService.findRoleByIdAndAllPermission(roleId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role",role);
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId,@RequestParam(name ="ids",required = true)String[] permissionIds){
        System.out.println(roleId);
        System.out.println(permissionIds[0]);
       iRoleService.addPermissionToRole(roleId,permissionIds);
       return "redirect:findAll.do";
    }

}
