package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.Repository.IMarcaDao;
import com.sistema.apirest.entity.Marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class IMarcaService {

    @Autowired
	IMarcaDao marcaRepository;
	
	public List<Marca> getMarcas(){
		return marcaRepository.findAll();
	}
    
}
