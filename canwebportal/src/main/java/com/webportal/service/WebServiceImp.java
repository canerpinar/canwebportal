package com.webportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.webportal.entity.Menu;
import com.webportal.repository.WebRepository;

@Service
public class WebServiceImp implements WebService {

	@Autowired
	private WebRepository webRepository;
	
	
	@Override
	public List<Menu> getListMenu() {
		return webRepository.getMenuList();
	}
	

	@Override
	public void saveMenu(Menu menu){
		webRepository.save(menu);
	}
}
