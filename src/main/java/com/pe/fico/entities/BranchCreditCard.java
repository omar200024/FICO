package com.pe.fico.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BranchCreditCard")
public class BranchCreditCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBranchCard;

	@Column(name = "nameBranch", length = 40, nullable = false)
	private String nameBranchCredit;

	public BranchCreditCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BranchCreditCard(int idBranchCard, String nameBranchCredit) {
		super();
		this.idBranchCard = idBranchCard;
		this.nameBranchCredit = nameBranchCredit;
	}

	public int getIdBranchCard() {
		return idBranchCard;
	}

	public void setIdBranchCard(int idBranchCard) {
		this.idBranchCard = idBranchCard;
	}

	public String getNameBranchCredit() {
		return nameBranchCredit;
	}

	public void setNameBranchCredit(String nameBranchCredit) {
		this.nameBranchCredit = nameBranchCredit;
	}

}
