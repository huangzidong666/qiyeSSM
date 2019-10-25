package com.huangjindong.controller;


import com.github.pagehelper.PageInfo;
import com.huangjindong.domian.Orders;
import com.huangjindong.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService iOrdersService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name ="page",required = true,defaultValue = "1")int page,@RequestParam(name = "size",required = true,defaultValue = "4")int size){
        ModelAndView modelAndView = new ModelAndView();

        List<Orders> ordersList = iOrdersService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(ordersList);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("orders-list");
        return modelAndView;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id){
        System.out.println("进来了。。。。。");
        ModelAndView modelAndView = new ModelAndView();
        Orders orders = iOrdersService.findById(id);
        System.out.println(orders.getId());


        modelAndView.addObject("orders",orders);
        modelAndView.setViewName("orders-show");
        System.out.println("出来了----------");
        return modelAndView;
    }
}
