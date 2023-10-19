package com.anirban.learnspringsecurity.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anirban.learnspringsecurity.entities.UserInfo;

public interface UserInfoDao extends JpaRepository<UserInfo,Integer> {

	
	
	Optional<UserInfo> findByName(String username);

	

	

}
