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
@Table(name = "CreditCardProduct")

public class CreditCardProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCreditCard;

	@Column(name = "benefitCreditCard", nullable = false)
	private String benefitCreditCard;

	@Column(name = "memebershipCreditCard", nullable = false)
	private float memebershipCreditCard;

	@ManyToOne
	@JoinColumn(name = "idBranch", nullable = false)
	private BranchCreditCard branchcredit;

	@ManyToOne
	@JoinColumn(name = "idProduct", nullable = false)
	private Product product;

	// Constructor
	public CreditCardProduct() {
		super();
	}

	public CreditCardProduct(int idCreditCard, String benefitCreditCard, float memebershipCreditCard,
			BranchCreditCard branchcredit, Product product) {
		super();
		this.idCreditCard = idCreditCard;
		this.benefitCreditCard = benefitCreditCard;
		this.memebershipCreditCard = memebershipCreditCard;
		this.branchcredit = branchcredit;
		this.product = product;
	}

	// Getters and Setters

	public int getIdCreditCard() {
		return idCreditCard;
	}

	public void setIdCreditCard(int idCreditCard) {
		this.idCreditCard = idCreditCard;
	}

	public BranchCreditCard getBranchcredit() {
		return branchcredit;
	}

	public void setBranchcredit(BranchCreditCard branchcredit) {
		this.branchcredit = branchcredit;
	}

	public String getBenefitCreditCard() {
		return benefitCreditCard;
	}

	public void setBenefitCreditCard(String benefitCreditCard) {
		this.benefitCreditCard = benefitCreditCard;
	}

	public float getMemebershipCreditCard() {
		return memebershipCreditCard;
	}

	public void setMemebershipCreditCard(float memebershipCreditCard) {
		this.memebershipCreditCard = memebershipCreditCard;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
