package com.pe.fico.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.fico.entities.Users;
import com.pe.fico.repositories.UserRepository;
import com.pe.fico.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	UserRepository uR;
	
	@Override
	public Integer insert(Users user) {
		int rpta = uR.buscarUsername(user.getUsername());
		if (rpta == 0) {
			uR.save(user);
		}
		return rpta;
	}

	@Override
	public List<Users> list() {
		return uR.findAll();
	}
	

	@Override
	@Transactional(readOnly = true)
	public Users listarId(long idUsers) {
		Optional<Users> op = uR.findById(idUsers);
		return op.isPresent() ? op.get() : new Users();
	}

	@Override
	public void delete(long id) {
		uR.deleteById(id);
		
	}

	@Override
	public boolean insertboo(Users user) {
		Users objUser = uR.save(user);
		if (objUser == null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<Users> findByNameUsers(String name) {
		// TODO Auto-generated method stub
		return uR.findByNameUsers(name);
	}

	@Override
	public List<Users> findByNameLikeIgnoreCase(String name) {
		// TODO Auto-generated method stub
		return uR.findByNameLikeIgnoreCase(name);
	}

	@Override
	@Transactional(readOnly = true)
	public Users listName(String name) {
		Optional<Users> op = uR.findByName(name);
		return op.isPresent() ? op.get() : new Users();
	}

}
