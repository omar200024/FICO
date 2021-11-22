package com.pe.fico.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.fico.entities.MethodComitionAFP;
import com.pe.fico.repositories.IMethodAfpRepository;
import com.pe.fico.service.IMethodAfpService;

@Service
public class MethodComitionServiceImpl implements IMethodAfpService {

	@Autowired
	private IMethodAfpRepository mR ;
	
	@Override
	public Integer insert(MethodComitionAFP category) {
		// TODO Auto-generated method stub
		int rpta=mR.searchMethod(category.getNameMethodAFP());
		if(rpta==0) {
			mR.save(category);
		}
		return rpta;
	}

	@Override
	public List<MethodComitionAFP> list() {
		// TODO Auto-generated method stub
		return mR.findAll();
	}

	
	
}
