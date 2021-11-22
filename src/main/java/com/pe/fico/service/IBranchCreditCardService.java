package com.pe.fico.service;

import java.util.List;

import com.pe.fico.entities.BranchCreditCard;

public interface IBranchCreditCardService {

	public Integer insert(BranchCreditCard category);

	List<BranchCreditCard> list();
}
