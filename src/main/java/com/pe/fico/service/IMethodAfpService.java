package com.pe.fico.service;

import java.util.List;

import com.pe.fico.entities.MethodComitionAFP;

public interface IMethodAfpService {

	public Integer insert(MethodComitionAFP category);

	List<MethodComitionAFP> list();
}
