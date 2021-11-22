package com.pe.fico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pe.fico.entities.CategoryProduct;

@Repository
public interface ICategoryProductRepository extends JpaRepository<CategoryProduct, Integer> {
	
	@Query("select count(c.nameCategoryProduct) from CategoryProduct c where c.nameCategoryProduct=:clave")
	public int searchCategory(@Param("clave") String name);
	
}
