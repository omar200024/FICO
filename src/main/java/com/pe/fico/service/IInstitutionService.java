package com.pe.fico.service;

import java.util.List;

import com.pe.fico.entities.Institution;

public interface IInstitutionService {
	
	public boolean insert(Institution institution);

	List<Institution> list();

	Institution listarId(int idInstitution);
}
