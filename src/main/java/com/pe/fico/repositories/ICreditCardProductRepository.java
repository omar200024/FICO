package com.pe.fico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pe.fico.entities.CreditCardProduct;

@Repository
public interface ICreditCardProductRepository extends JpaRepository<CreditCardProduct, Integer> {

	@Query("select c from CreditCardProduct c where c.benefitCreditCard like %:benefit%")
	List<CreditCardProduct> findByBenefit(String benefit);
}
