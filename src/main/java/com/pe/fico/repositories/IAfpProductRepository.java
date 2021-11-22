package com.pe.fico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pe.fico.entities.AfpProduct;

@Repository
public interface IAfpProductRepository extends JpaRepository<AfpProduct, Integer>{

	@Query("select c from AfpProduct c where c.typeAfp like %:type%")
	List<AfpProduct> findByType(String type);
}
