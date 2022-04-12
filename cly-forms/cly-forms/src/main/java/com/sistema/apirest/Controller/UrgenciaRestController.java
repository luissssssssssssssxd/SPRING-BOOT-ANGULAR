package com.sistema.apirest.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apirest.Service.IUrgenciaService;
import com.sistema.apirest.Service.UrgenciaServiceImpl;
import com.sistema.apirest.entity.Urgencia;

@CrossOrigin(origins = { "http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class UrgenciaRestController {

	@Autowired
	private IUrgenciaService iUrgenciaService;
	

	@GetMapping("/urgencias")
	public List<Urgencia> index() {
		return iUrgenciaService.findAll();
	}

	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/urgencias/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Urgencia  urgencia = null;
		Map<String, Object> response = new HashMap<>();

		try {
		urgencia = iUrgenciaService.findbyid(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return  new  ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
//		Si registro buscado en NULO
		if(urgencia == null) {
			response.put("mensaje", "La Ficha ID:  ".concat(id.toString().concat(" No existe en la BD")));
			return  new  ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		return  new  ResponseEntity<>(urgencia,HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/urgencias")
	public ResponseEntity<?> create(@Valid  @RequestBody Urgencia urgencia,BindingResult bindingResult) {
		Urgencia  urgencianew = null;
		Map<String, Object> response = new HashMap<>();
		
		
		if(bindingResult.hasErrors()) {
//			List<String> errors = new ArrayList<>();
//			
//			for(FieldError err: bindingResult.getFieldErrors()) {
//				errors.add("El campo"+ err.getField() +  " '"+ err.getDefaultMessage());
//			}
			
			List<String> errors = bindingResult.getFieldErrors()
					.stream()
					.map(err -> {
						return "El campo '" + err.getField() + "' " +err.getDefaultMessage();
						
					})
					.collect(Collectors.toList());
					;
				
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);

		}

		try {
		urgencianew =	 iUrgenciaService.save(urgencia);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La ficha ha sido creado con exito");
		response.put("urgencia", urgencianew);
			
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

	}

	
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/urgencias/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Urgencia urgencia,BindingResult result, @PathVariable Long id) {
		
		Urgencia datoactual = iUrgenciaService.findbyid(id);
		Urgencia urgupdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
//			List<String> errors = new ArrayList<>();
//			
//			for(FieldError err: bindingResult.getFieldErrors()) {
//				errors.add("El campo"+ err.getField() +  " '"+ err.getDefaultMessage());
//			}
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(e -> {
						return "El campo '" + e.getField() + "' " +e.getDefaultMessage();
						
					})
					.collect(Collectors.toList());
					;
				
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);

		}

		
		//Si registro buscado en NULO
		if(datoactual == null) {
			response.put("mensaje", "Error: no se pudo editar, la ficha ID;   ".concat(id.toString().concat(" No existe en la BD")));
			return  new  ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		try {
			datoactual.setNombreacompanante(urgencia.getNombreacompanante());
			datoactual.setNombrepaciente(urgencia.getNombrepaciente());
			datoactual.setObservacion(urgencia.getObservacion());
			datoactual.setHoraespera(urgencia.getHoraespera());
			urgupdated= iUrgenciaService.save(datoactual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "La ficha ha sido actualizada con exito");
		response.put("urgencia", urgupdated);
			
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	

	}

	
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/urgencias/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();

		try {
			iUrgenciaService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "La ficha ha sido eliminada con exito");	
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	


}
