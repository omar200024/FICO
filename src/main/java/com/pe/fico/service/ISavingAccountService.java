package com.pe.fico.service;

import java.util.List;

import com.pe.fico.entities.SavingAccountProduct;

public interface ISavingAccountService {
	public boolean insert(SavingAccountProduct idSavingAccount);

	List<SavingAccountProduct> list();

	SavingAccountProduct listarId(int idSavingAccount);
	
	public void delSAbyId(int idSA);
	
	List<SavingAccountProduct> findByOp(int op);
}