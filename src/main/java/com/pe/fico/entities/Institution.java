package com.pe.fico.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Institution")
public class Institution {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idInstitution;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre de la institución no puede contener un caracter especial")
	@Pattern(regexp = "[^0-9]+", message = "El nombre de la institución no puede contener un número")
	@Column(name = "nameInstitution", length = 40, nullable = false)
	private String nameInstitution;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El tipo de institución no puede contener un caracter especial")
	@Pattern(regexp = "[^0-9]+", message = "El tipo de institución no puede contener un número")
	@Column(name = "typeInstitution", length = 40, nullable = false)
	private String typeInstitution;

	@Column(name = "urlInstitution", length = 100, nullable = false)
	private String urlInstitution;

	@Column(name = "photoInstitution", nullable = true)
	private String photoInstitution;

	// Construct
	public Institution() {
		super();
	}

	public Institution(int idInstitution,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre de la institución no puede contener un caracter especial") @Pattern(regexp = "[^0-9]+", message = "El nombre de la institución no puede contener un número") String nameInstitution,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El tipo de institución no puede contener un caracter especial") @Pattern(regexp = "[^0-9]+", message = "El tipo de institución no puede contener un número") String typeInstitution,
			String urlInstitution, String photoInstitution) {
		super();
		this.idInstitution = idInstitution;
		this.nameInstitution = nameInstitution;
		this.typeInstitution = typeInstitution;
		this.urlInstitution = urlInstitution;
		this.photoInstitution = photoInstitution;
	}

	//Getters and Setters
	public int getIdInstitution() {
		return idInstitution;
	}

	public void setIdInstitution(int idInstitution) {
		this.idInstitution = idInstitution;
	}

	public String getNameInstitution() {
		return nameInstitution;
	}

	public void setNameInstitution(String nameInstitution) {
		this.nameInstitution = nameInstitution;
	}

	public String getTypeInstitution() {
		return typeInstitution;
	}

	public void setTypeInstitution(String typeInstitution) {
		this.typeInstitution = typeInstitution;
	}

	public String getUrlInstitution() {
		return urlInstitution;
	}

	public void setUrlInstitution(String urlInstitution) {
		this.urlInstitution = urlInstitution;
	}

	public String getPhotoInstitution() {
		return photoInstitution;
	}

	public void setPhotoInstitution(String photoInstitution) {
		this.photoInstitution = photoInstitution;
	}

}
