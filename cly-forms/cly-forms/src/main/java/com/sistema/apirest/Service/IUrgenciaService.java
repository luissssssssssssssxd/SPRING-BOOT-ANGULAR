package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.entity.Urgencia;

public interface IUrgenciaService {
	
	public List<Urgencia> findAll();
	
	public Urgencia save(Urgencia urgencia);
	
	public void delete(Long id);
	
	public Urgencia findbyid(Long id);

}
