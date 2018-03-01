package com.webportal.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.webportal.entity.Menu;

@Repository
public interface WebRepository extends CrudRepository<Menu,Long>{
	@Query("select menu from Menu menu")
	List<Menu> getMenuList();
	
	
}
