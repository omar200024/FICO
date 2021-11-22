package com.pe.fico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pe.fico.entities.SavingAccountProduct;

@Repository
public interface ISavingAccountRepository extends JpaRepository<SavingAccountProduct, Integer> {
	@Query("select count(sa.minOpeningSaving) from SavingAccountProduct sa where sa.idSavingAccount=:name")
	public int buscarCuentaAhorro(@Param("name") String nombre);
	
	@Query("select c from SavingAccountProduct c where c.freeOperationSA >= :op")
	List<SavingAccountProduct> findByOper(int op);
}
