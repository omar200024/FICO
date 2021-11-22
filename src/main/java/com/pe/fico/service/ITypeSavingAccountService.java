package com.pe.fico.service;

import java.util.List;

import com.pe.fico.entities.TypeSavingAccount;


public interface ITypeSavingAccountService {
	public Integer insert(TypeSavingAccount TipoCuentaAhorro);

	List<TypeSavingAccount> list();
}
