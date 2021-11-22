package com.pe.fico.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.fico.entities.Role;
import com.pe.fico.repositories.RoleRepository;
import com.pe.fico.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	RoleRepository rR;
	
	@Override
	public void insert(Role rol) {
			rR.save(rol);
	}

	@Override
	public List<Role> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}

	@Override
	public void delete(long id) {
		rR.deleteById(id);
	}

	@Override
	public Optional<Role> listarId(long id) {
		// TODO Auto-generated method stub
		return rR.findById(id);
	}

	@Override
	public List<String[]> rolXedad() {
		// TODO Auto-generated method stub
		return rR.rolXedad();
	}

}
