package com.huangjindong.service;



import com.huangjindong.domian.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void addProduct(Product product);
}
