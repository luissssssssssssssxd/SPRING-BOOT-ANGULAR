package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.entity.Modelo;

public interface IModeloService {

    public List<Modelo> findAll();

    public Modelo save(Modelo modelo);
	
	public void delete(Long id);
	
	public Modelo findbyid(Long id);


    
}
