package com.pe.fico.repositories;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pe.fico.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	
	public Users findByUsername(String username);
	
	@Query("select count(u.username) from Users u where u.username =:username")
	public int buscarUsername(@Param("username") String nombre);

	@Transactional
	@Modifying
	@Query(value = "insert into roles (rol, user_id) VALUES (:rol, :user_id)", nativeQuery = true)
	public void insRol(@Param("rol") String authority, @Param("user_id") Long user_id);
	
	@Query("select c from Users c where c.name like %:name%")
	List<Users> findByNameUsers(String name);
	
	List<Users> findByNameLikeIgnoreCase(String name);
	
	@Query("select c from Users c where c.username like %:name%")
	Optional<Users> findByName(String name);
}