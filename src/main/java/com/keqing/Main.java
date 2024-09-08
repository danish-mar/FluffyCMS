package com.keqing;


import com.keqing.model.Product;
import com.keqing.model.ProductRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

class Main{

    public static void main(String[] args) {

        ProductRepository pr = new ProductRepository("localhost:3306/bakery","root","rootpassword");
        List<Product> products = pr.findAll();

//        Product newProduct = new Product(0,"Honey Bun","Sweet Honey Buns",1,200,5,5,"cdn/cakes/chocolate_rainbow.png",new Date(25252525),true);
//        pr.save(newProduct);
//        System.out.println(pr.findByAvailability(true));

//
//        Timestamp fromDate = Timestamp.valueOf("2024-09-01 00:00:00");
//        Timestamp toDate = Timestamp.valueOf("2024-09-30 23:59:59");
//
//        pr.addProductToFeatured(11, fromDate, toDate);
        pr.removeProductFromFeatured(11);


    }

}