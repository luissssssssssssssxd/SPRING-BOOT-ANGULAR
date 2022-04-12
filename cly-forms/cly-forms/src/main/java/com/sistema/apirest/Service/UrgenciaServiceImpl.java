package com.sistema.apirest.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.apirest.Repository.IUrgenciaDao;
import com.sistema.apirest.entity.Urgencia;


@Service
public class UrgenciaServiceImpl implements IUrgenciaService {
	
	
	@Autowired
	private IUrgenciaDao urgenciadao;
	
	
	

	@Override
	@Transactional(readOnly = true)
	public List<Urgencia> findAll() {
		return (List<Urgencia>) urgenciadao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Urgencia findbyid(Long id) {
		return urgenciadao.findById(id).orElse(null);
	}


	@Override
	@Transactional
	public Urgencia save(Urgencia urgencia) {
		return urgenciadao.save(urgencia);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		urgenciadao.deleteById(id);
		
	}
	



}
