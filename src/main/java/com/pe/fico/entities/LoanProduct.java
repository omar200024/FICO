package com.pe.fico.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LoanProduct")
public class LoanProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLoanPoduct;

	@Column(name = "idType", nullable = false)
	private String typeLoan;

	@Column(name = "currencyLoan", nullable = false)
	private String currencyLoan;

	@Column(name = "minAmountLoan", nullable = false)
	private float minAmountLoan;

	@Column(name = "maxAmountLoan", nullable = false)
	private float maxAmountLoan;

	@ManyToOne
	@JoinColumn(name = "idProduct", nullable = false)
	private Product product;

	// Constructor
	public LoanProduct() {
		super();
	}

	public LoanProduct(int idLoanPoduct, String typeLoan, String currencyLoan, float minAmountLoan,
			float maxAmountLoan, Product product) {
		super();
		this.idLoanPoduct = idLoanPoduct;
		this.typeLoan = typeLoan;
		this.currencyLoan = currencyLoan;
		this.minAmountLoan = minAmountLoan;
		this.maxAmountLoan = maxAmountLoan;
		this.product = product;
	}

	// Getters and Setters
	public int getIdLoanPoduct() {
		return idLoanPoduct;
	}

	public void setIdLoanPoduct(int idLoanPoduct) {
		this.idLoanPoduct = idLoanPoduct;
	}

	public String getTypeLoan() {
		return typeLoan;
	}

	public void setTypeLoan(String typeLoan) {
		this.typeLoan = typeLoan;
	}

	public String getCurrencyLoan() {
		return currencyLoan;
	}

	public void setCurrencyLoan(String currencyLoan) {
		this.currencyLoan = currencyLoan;
	}

	public float getMinAmountLoan() {
		return minAmountLoan;
	}

	public void setMinAmountLoan(float minAmountLoan) {
		this.minAmountLoan = minAmountLoan;
	}

	public float getMaxAmountLoan() {
		return maxAmountLoan;
	}

	public void setMaxAmountLoan(float maxAmountLoan) {
		this.maxAmountLoan = maxAmountLoan;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idLoanPoduct;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanProduct other = (LoanProduct) obj;
		if (idLoanPoduct != other.idLoanPoduct)
			return false;
		return true;
	}

}
