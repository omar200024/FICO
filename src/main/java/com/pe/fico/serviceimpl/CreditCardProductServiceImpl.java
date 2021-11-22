package com.pe.fico.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.fico.entities.CreditCardProduct;
import com.pe.fico.repositories.ICreditCardProductRepository;
import com.pe.fico.service.ICreditCardProductService;

@Service
public class CreditCardProductServiceImpl implements ICreditCardProductService {

	@Autowired
	private ICreditCardProductRepository cR;
	
	@Override
	public boolean insert(CreditCardProduct product) {
		CreditCardProduct objProduct = cR.save(product);
		if (objProduct == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<CreditCardProduct> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public CreditCardProduct listarId(int idCredit) {
		// TODO Auto-generated method stub
		Optional<CreditCardProduct> op=cR.findById(idCredit);
		return op.isPresent()?op.get():new CreditCardProduct();
	}
	
	@Override
	public void delCredbyId(int idAfp) {
		cR.deleteById(idAfp);
	}

	@Override
	public List<CreditCardProduct> findByBenefit(String benefit) {
		// TODO Auto-generated method stub
		return cR.findByBenefit(benefit);
	}
}
