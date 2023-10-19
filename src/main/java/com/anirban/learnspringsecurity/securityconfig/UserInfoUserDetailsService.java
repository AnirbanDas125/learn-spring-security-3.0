package com.anirban.learnspringsecurity.securityconfig;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.anirban.learnspringsecurity.dao.UserInfoDao;
import com.anirban.learnspringsecurity.entities.UserInfo;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserInfoDao userInfoDao;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	 Optional<UserInfo> userInfo = userInfoDao.findByName(username);
	 
	 if(userInfo.isEmpty()) {
		 throw new UsernameNotFoundException("User not found");
	 }
		return new UserInfoUserDetails(userInfo.get());
	}

}
