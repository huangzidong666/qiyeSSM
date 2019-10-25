package com.huangjindong.dao;


import com.huangjindong.domian.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {
    @Select("select * from product")
    List<Product> findAll();
    @Insert("insert into Product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void addProduct(Product product);
    @Select("select * from product where id=#{id}")
    Product findById(String id);
}
