package com.pe.fico.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.fico.entities.BranchCreditCard;
import com.pe.fico.repositories.IBranchCreditCardRepository;
import com.pe.fico.service.IBranchCreditCardService;

@Service
public class BranchCreditCardServiceImpl implements IBranchCreditCardService{

	@Autowired
	private IBranchCreditCardRepository bR;
	
	@Override
	public Integer insert(BranchCreditCard category) {
		// TODO Auto-generated method stub
		int rpta=bR.searchBranch(category.getNameBranchCredit());
		if(rpta==0) {
			bR.save(category);
		}
		return rpta;
	}

	@Override
	public List<BranchCreditCard> list() {
		// TODO Auto-generated method stub
		return bR.findAll();
	}

}
