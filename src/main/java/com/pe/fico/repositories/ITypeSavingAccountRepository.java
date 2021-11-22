package com.pe.fico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pe.fico.entities.TypeSavingAccount;

public interface ITypeSavingAccountRepository extends JpaRepository<TypeSavingAccount, Integer>{
	@Query("select count(tp.nameTypeSavingAccount) from TypeSavingAccount tp where tp.nameTypeSavingAccount=:name")
	public int buscarTipoCuentaAhorro(@Param("name") String nombre);
}
