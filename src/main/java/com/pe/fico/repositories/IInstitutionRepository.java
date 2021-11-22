package com.pe.fico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pe.fico.entities.Institution;

@Repository
public interface IInstitutionRepository extends JpaRepository<Institution, Integer> {

	@Query("select count(c.nameInstitution) from Institution c where c.nameInstitution=:clave")
	public int searchInstitution(@Param("clave") String name);
	
}
