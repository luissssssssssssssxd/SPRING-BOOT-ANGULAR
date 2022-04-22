package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.entity.Piso;

public interface IPisoService {

    public List<Piso> findAll();

    public Piso save(Piso piso);
	
	public void delete(Long id);
	
	public Piso findbyid(Long id);
    
}
