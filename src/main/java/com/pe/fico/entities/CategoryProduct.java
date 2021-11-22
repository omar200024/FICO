package com.pe.fico.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "CategoryProduct")
public class CategoryProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategoryProduct;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre de la categoria no puede contener un caracter especial")
	@Pattern(regexp = "[^0-9]+", message = "El nombre de la categoria no puede contener un número")
	@Column(name = "nameCategoryProduct", length = 40, nullable = false)
	private String nameCategoryProduct;

	public CategoryProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryProduct(int idCategoryProduct,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre de la categoria no puede contener un caracter especial") @Pattern(regexp = "[^0-9]+", message = "El nombre de la categoria no puede contener un número") String nameCategoryProduct) {
		super();
		this.idCategoryProduct = idCategoryProduct;
		this.nameCategoryProduct = nameCategoryProduct;
	}

	public int getIdCategoryProduct() {
		return idCategoryProduct;
	}

	public void setIdCategoryProduct(int idCategoryProduct) {
		this.idCategoryProduct = idCategoryProduct;
	}

	public String getNameCategoryProduct() {
		return nameCategoryProduct;
	}

	public void setNameCategoryProduct(String nameCategoryProduct) {
		this.nameCategoryProduct = nameCategoryProduct;
	}
}