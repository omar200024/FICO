package com.pe.fico.service;

import java.util.List;

import com.pe.fico.entities.AfpProduct;

public interface IAfpProductService {

	public boolean insert(AfpProduct afp);

	List<AfpProduct> list();

	AfpProduct listarId(int idAfp);
	
	public void delAfpbyId(int idAfp);
	
	List<AfpProduct> findByTypeAfp(String type);
}
