package com.pe.fico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pe.fico.entities.BranchCreditCard;

@Repository
public interface IBranchCreditCardRepository extends JpaRepository<BranchCreditCard, Integer>{

	@Query("select count(c.nameBranchCredit) from BranchCreditCard c where c.nameBranchCredit=:clave")
	public int searchBranch(@Param("clave") String name);
	
}
