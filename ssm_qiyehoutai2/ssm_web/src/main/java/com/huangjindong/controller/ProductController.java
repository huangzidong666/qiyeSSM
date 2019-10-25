package com.huangjindong.controller;



import com.huangjindong.domian.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.huangjindong.service.IProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView modeAndView=new ModelAndView();
        List<Product> productList = productService.findAll();
        modeAndView.addObject("productList",productList);
        modeAndView.setViewName("product-list");
        return modeAndView;
    }
    @RequestMapping("/addProduct.do")
    public String addProduct(Product product){
        productService.addProduct(product);
        return "redirect:findAll.do";
    }


}

