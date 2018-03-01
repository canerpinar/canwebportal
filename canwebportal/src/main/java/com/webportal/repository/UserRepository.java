package com.webportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webportal.entity.Users;

@Repository
public interface UserRepository extends CrudRepository<Users,Long> {

	@Query("select r.role from UserRole r,Users user where user.userName=:username and r.userId=user.id")
	public List<String> findRoleByUsername(@Param("username") String username);
	public Users findByUserName(String userName);
}
