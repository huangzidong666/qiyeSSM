package com.huangjindong.controller;

import com.huangjindong.domian.Role;
import com.huangjindong.domian.UserInfo;
import com.huangjindong.service.IUserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private IUserInfoService iUserInfoService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        List<UserInfo> userList = iUserInfoService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("user-list");
        return modelAndView;

    }
    @RequestMapping("/addUserInfo.do")
    public  String addUserInfo(UserInfo userInfo){
        iUserInfoService.addUserInfo(userInfo);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findById.do")
    public  ModelAndView findById(String id){
        ModelAndView modelAndView = new ModelAndView();
        UserInfo userInfo = iUserInfoService.findById(id);
        modelAndView.addObject("user",userInfo);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }
    @RequestMapping("findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name="id",required = true)String userid){
        ModelAndView modelAndView = new ModelAndView();
        UserInfo userInfo = iUserInfoService.findById(userid);
        List<Role> roleList = iUserInfoService.findUserByIdAddAllRole(userid);
        modelAndView.addObject("user",userInfo);
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }
    @RequestMapping("addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name ="userId",required = true)String userId,@RequestParam(name = "ids",required = true)String[] roleIds){
        System.out.println(userId);
        System.out.println(roleIds[0]);
        iUserInfoService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }
}
