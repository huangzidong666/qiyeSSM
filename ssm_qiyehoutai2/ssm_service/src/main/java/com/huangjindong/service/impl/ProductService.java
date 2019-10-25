package com.huangjindong.service.impl;




import com.huangjindong.dao.IProductDao;
import com.huangjindong.domian.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huangjindong.service.IProductService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    private IProductDao iProductDao;
    public List<Product> findAll() {

        return iProductDao.findAll();
    }

    @Override
    public void addProduct(Product product) {

        iProductDao.addProduct(product);
    }
}
