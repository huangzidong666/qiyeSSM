package com.huangjindong.service;

import com.huangjindong.domian.Orders;

import java.util.List;

public interface IOrdersService {
    List<Orders> findAll(int page,int size);

    Orders findById(String id);
}
