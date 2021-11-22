package com.pe.fico.service;

import java.util.List;

import com.pe.fico.entities.Product;

public interface IProductService {

	public boolean insert(Product product);

	List<Product> list();

	Product listarId(int idProducto);
}
