package com.huangjindong.controller;

import com.huangjindong.domian.Permission;
import com.huangjindong.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService iPermissionService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        List<Permission> permissionList = iPermissionService.findAll();
        System.out.println(permissionList.get(0).getPermissionName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.setViewName("permission-list");
        return modelAndView;

    }
    @RequestMapping("/addPermission")
    public String addPermission(Permission permission){
       iPermissionService.addPermission(permission);
       return "redirect:findAll.do";
    }
}
