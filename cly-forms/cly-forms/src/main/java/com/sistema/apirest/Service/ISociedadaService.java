package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.entity.Sociedades;

public interface ISociedadaService {
    
    public List<Sociedades> findAll();

    public Sociedades save(Sociedades sociedades);
	
	public void delete(Long id);
	
	public Sociedades findbyid(Long id);
    
}
