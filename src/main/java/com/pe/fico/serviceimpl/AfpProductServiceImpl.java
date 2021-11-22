package com.pe.fico.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.fico.entities.AfpProduct;
import com.pe.fico.repositories.IAfpProductRepository;
import com.pe.fico.service.IAfpProductService;

@Service
public class AfpProductServiceImpl implements IAfpProductService{

	@Autowired
	private IAfpProductRepository aR;
	
	@Override
	public boolean insert(AfpProduct afp) {
		// TODO Auto-generated method stub
		AfpProduct objAfp=aR.save(afp);
		if (objAfp == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<AfpProduct> list() {
		// TODO Auto-generated method stub
		return aR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public AfpProduct listarId(int idAfp) {
		// TODO Auto-generated method stub
		Optional<AfpProduct> op=aR.findById(idAfp);
		return op.isPresent()?op.get():new AfpProduct();
	}
	
	@Override
	public void delAfpbyId(int idAfp) {
		aR.deleteById(idAfp);
	}

	@Override
	public List<AfpProduct> findByTypeAfp(String type) {
		// TODO Auto-generated method stub
		return aR.findByType(type);
	}

	
}
