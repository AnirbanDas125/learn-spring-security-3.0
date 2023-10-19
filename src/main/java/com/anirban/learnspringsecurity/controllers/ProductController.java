package com.anirban.learnspringsecurity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anirban.learnspringsecurity.entities.UserInfo;
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //As a security feature we can map it in the controller, 
                                                //which role user is authorized to hit what endpoint
    public List<Product> getAllTheProducts() {                                                
        return service.getAllProducts();
    }
    
//e.g we made this /{id} endpoint available for both normal user and admin user but /all endpoint is only available for admin users
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")  
    public Product getProductById(@PathVariable int id) {
        return service.getProductById(id);
    }
    
    @PostMapping("/addNewUser")
    public String addUser(@RequestBody UserInfo userInfo) {
    	return service.addNewUser(userInfo);
    }
    
 

}
