package com.pe.fico.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.fico.entities.Product;
import com.pe.fico.repositories.IProductRepository;
import com.pe.fico.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository pR;

	@Override
	public boolean insert(Product product) {
		Product objProduct = pR.save(product);
		if (objProduct == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<Product> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Product listarId(int idProduct) {
		Optional<Product> op = pR.findById(idProduct);
		return op.isPresent() ? op.get() : new Product();
	}

}
