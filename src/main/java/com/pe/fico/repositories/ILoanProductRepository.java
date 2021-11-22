package com.pe.fico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pe.fico.entities.LoanProduct;

@Repository
public interface ILoanProductRepository extends JpaRepository<LoanProduct, Integer> {
	
	@Query("select c from LoanProduct c where c.currencyLoan like %:currency%")
	List<LoanProduct> findByCurrency(String currency);

}
