package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.entity.CentroCosto;

public interface ICentroCostoService {

    public List<CentroCosto> findAll();

    public CentroCosto save(CentroCosto centroCosto);
	
	public void delete(Long id);
	
	public CentroCosto findbyid(Long id);
    
}
