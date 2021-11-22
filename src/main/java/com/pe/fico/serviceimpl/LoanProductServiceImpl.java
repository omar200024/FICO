package com.pe.fico.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.fico.entities.LoanProduct;
import com.pe.fico.repositories.ILoanProductRepository;
import com.pe.fico.service.ILoanProductService;

@Service
public class LoanProductServiceImpl implements ILoanProductService {

	@Autowired
	private ILoanProductRepository lR;

	@Override
	public boolean insert(LoanProduct loan) {
		// TODO Auto-generated method stub
		LoanProduct objLoan = lR.save(loan);
		if (objLoan == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<LoanProduct> list() {
		// TODO Auto-generated method stub
		return lR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public LoanProduct listarId(int idLoan) {
		// TODO Auto-generated method stub
		Optional<LoanProduct> op = lR.findById(idLoan);
		return op.isPresent() ? op.get() : new LoanProduct();
	}
	
	@Override
	public void delLoanbyId(int idAfp) {
		lR.deleteById(idAfp);
	}

	@Override
	public List<LoanProduct> findByCurrency(String currency) {
		// TODO Auto-generated method stub
		return lR.findByCurrency(currency);
	}

}
