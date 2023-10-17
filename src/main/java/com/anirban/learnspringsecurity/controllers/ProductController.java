package com.anirban.learnspringsecurity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anirban.learnspringsecurity.models.Product;
import com.anirban.learnspringsecurity.services.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {
	
	

    @Autowired
    private ProductService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the product catalog";
    }

  

    @GetMapping("/all")
   
    public List<Product> getAllTheProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return service.getProductById(id);
    }
    
 

}
