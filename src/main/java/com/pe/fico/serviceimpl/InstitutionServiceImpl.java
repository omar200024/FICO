package com.pe.fico.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.fico.entities.Institution;
import com.pe.fico.repositories.IInstitutionRepository;
import com.pe.fico.service.IInstitutionService;

@Service
public class InstitutionServiceImpl implements IInstitutionService {

	@Autowired
	private IInstitutionRepository iR;

	@Override
	public boolean insert(Institution institution) {
		// TODO Auto-generated method stub
		Institution objInstitution = iR.save(institution);
		if (objInstitution == null) {
			return false;

		} else {
			return true;
		}

	}

	@Override
	public List<Institution> list() {
		// TODO Auto-generated method stub
		return iR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Institution listarId(int idInstitution) {
		// TODO Auto-generated method stub
		Optional<Institution> op = iR.findById(idInstitution);
		return op.isPresent() ? op.get() : new Institution();
	}

}
