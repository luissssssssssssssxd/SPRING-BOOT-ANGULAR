package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.entity.Ubicacion;

public interface IUbicacionService {

    public List<Ubicacion> findAll();

    public Ubicacion save(Ubicacion ubicacion);
	
	public void delete(Long id);
	
	public Ubicacion findbyid(Long id);
    
}
