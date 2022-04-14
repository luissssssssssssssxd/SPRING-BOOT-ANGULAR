package com.sistema.apirest.Controller;

import java.util.List;

import com.sistema.apirest.Service.IMarcaService;
import com.sistema.apirest.entity.Marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class MarcaController {

    @Autowired
	private IMarcaService marcaService;



    
	@GetMapping("/marcas")
	public List<Marca> getMarcas(){
		return marcaService.getMarcas();
	}
    
}
