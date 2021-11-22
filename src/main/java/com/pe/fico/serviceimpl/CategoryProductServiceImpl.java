package com.pe.fico.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.fico.entities.CategoryProduct;
import com.pe.fico.repositories.ICategoryProductRepository;
import com.pe.fico.service.ICategoryProductService;

@Service
public class CategoryProductServiceImpl implements ICategoryProductService {

	@Autowired
	private ICategoryProductRepository cR;
	
	@Override
	public Integer insert(CategoryProduct category) {
		// TODO Auto-generated method stub
		int rpta=cR.searchCategory(category.getNameCategoryProduct());
		if(rpta==0) {
			cR.save(category);
		}
		return rpta;
	}

	@Override
	public List<CategoryProduct> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

}
