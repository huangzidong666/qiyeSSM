package com.huangjindong.controller;

import com.huangjindong.domian.SysLog;
import com.huangjindong.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private ISysLogService iSysLogService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<SysLog> sysLogList = iSysLogService.findAll();
        modelAndView.addObject("sysLogs",sysLogList);
        modelAndView.setViewName("syslog-list");
        return modelAndView;
    }
}
