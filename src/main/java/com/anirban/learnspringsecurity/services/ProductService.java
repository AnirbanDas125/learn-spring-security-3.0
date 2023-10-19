package com.anirban.learnspringsecurity.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.anirban.learnspringsecurity.dao.UserInfoDao;
import com.anirban.learnspringsecurity.entities.UserInfo;
import com.anirban.learnspringsecurity.models.Product;

import jakarta.annotation.PostConstruct;

@Service
public class ProductService {
	

    List<Product> productList = new ArrayList<>();
    
    @Autowired
    UserInfoDao userInfoDao;
   
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void loadProductsFromDB() {
       for(int i=1;i<=50;i++) {
    	   Random random = new Random();
    	   Product pd = new Product();
    	   pd.setProductId(i);
    	   pd.setName("Product "+i);
    	   pd.setQty(random.nextInt(10) + 1);
    	   pd.setPrice(Math.round(random.nextDouble(100,200)*100.0)/100.0 );
    	   productList.add(pd);
       }
    }


    public List<Product> getAllProducts() {
        return productList;
    }
    
    public Product getProductById(int id) {
    	for(Product p : productList) {
    		if(p.getProductId() == id) {
    		  return p;	
    		}
    	}
    	
    	return null;
    }


	public String addNewUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfoDao.save(userInfo);
		
		return "user created by id ";
	}

}
