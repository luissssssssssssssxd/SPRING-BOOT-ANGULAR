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

	public List<Marca> getMarcas() {
		return marcaRepository.findAll();
	}

	public Marca save(Marca marca) {
		return marcaRepository.save(marca);
	}

	public void delete(Long id) {
		marcaRepository.deleteById(id);
	}

	public Marca findbyid(Long id) {
		return marcaRepository.findById(id).orElse(null);
	}

}
