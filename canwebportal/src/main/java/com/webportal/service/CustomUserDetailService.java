package com.webportal.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webportal.entity.Users;
import com.webportal.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	private UserRepository userRepository;
	
	public CustomUserDetailService(UserRepository repository){
		this.userRepository = repository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByUserName(username);
		if(user == null){
			throw new UsernameNotFoundException("not found ".concat(username));
		}else{
			List<String> userRoleList = userRepository.findRoleByUsername(username);
			return new CustomUserDetails(user, userRoleList);
		}
		
	}

}
