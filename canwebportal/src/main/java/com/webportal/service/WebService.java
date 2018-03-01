package com.webportal.service;

import java.util.List;



import com.webportal.entity.Menu;

public interface WebService {
	List<Menu> getListMenu();
	public void saveMenu(Menu menu);
}
