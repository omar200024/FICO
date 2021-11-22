package com.pe.fico.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduct;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre de la institución no puede contener un caracter especial")
	@Pattern(regexp = "[^0-9]+", message = "El nombre de la institución no puede contener un número")
	@Column(name = "nameProduct", length = 45, nullable = false)
	private String nameProduct;

	@NotNull
	@Column(name = "ratingProduct", nullable = false)
	private float ratingProduct;

	@NotNull
	@Column(name = "rateproduct", nullable = true)
	private float rateProduct;

	@ManyToOne
	@JoinColumn(name = "idCategoryproduct", nullable = false)
	private CategoryProduct category;

	@ManyToOne
	@JoinColumn(name = "idInstitution", nullable = false)
	private Institution institution;

	public Product() {
		super();
	}

	public Product(int idProduct, String nameProduct, float ratingProduct, float rateProduct, CategoryProduct category,
			Institution institution) {
		super();
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.ratingProduct = ratingProduct;
		this.rateProduct = rateProduct;
		this.category = category;
		this.institution = institution;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public float getRatingProduct() {
		return ratingProduct;
	}

	public void setRatingProduct(float ratingProduct) {
		this.ratingProduct = ratingProduct;
	}

	public float getRateProduct() {
		return rateProduct;
	}

	public void setRateProduct(float rateProduct) {
		this.rateProduct = rateProduct;
	}

	public CategoryProduct getCategory() {
		return category;
	}

	public void setCategory(CategoryProduct category) {
		this.category = category;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

}
