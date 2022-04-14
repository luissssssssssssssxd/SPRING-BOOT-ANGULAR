package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.entity.Estado_imprsora;

public interface IEstadoService {
    public List<Estado_imprsora> findAll();
	
	public Estado_imprsora save(Estado_imprsora estado);
	
	public void delete(Long id);
	
	public Estado_imprsora findbyid(Long id);
    
}
