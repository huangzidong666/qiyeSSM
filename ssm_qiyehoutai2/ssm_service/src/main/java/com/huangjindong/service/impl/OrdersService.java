package com.huangjindong.service.impl;

import com.github.pagehelper.PageHelper;
import com.huangjindong.dao.IOrdersDao;
import com.huangjindong.domian.Orders;
import com.huangjindong.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersService implements IOrdersService {
    @Autowired
    private IOrdersDao iOrdersDao;
    @Override
    public List<Orders> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return iOrdersDao.findAll();
    }

    @Override
    public Orders findById(String id) {
        return iOrdersDao.findByid(id);
    }
}
