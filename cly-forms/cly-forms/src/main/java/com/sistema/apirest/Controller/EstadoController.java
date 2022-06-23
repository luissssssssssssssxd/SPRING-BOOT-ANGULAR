package com.sistema.apirest.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.sistema.apirest.Service.IEstadoService;
import com.sistema.apirest.entity.Estado_imprsora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")

public class EstadoController {

    @Autowired
    private IEstadoService estadoservice;
    


	
    @GetMapping("/estados")
    public List<Estado_imprsora> listar(){
        return estadoservice.findAll();
    }
    @Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/estados/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Estado_imprsora  estado = null;
		Map<String, Object> response = new HashMap<>();

		try {
		estado = estadoservice.findbyid(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return  new  ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
//		Si registro buscado en NULO
		if(estado == null) {
			response.put("mensaje", "El estado  ID:  ".concat(id.toString().concat(" No existe en la BD")));
			return  new  ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		return  new  ResponseEntity<>(estado,HttpStatus.OK);
	}


    @Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/estados")
	public ResponseEntity<?> create(@Valid  @RequestBody Estado_imprsora estado,BindingResult bindingResult) {
		Estado_imprsora  estadonew = null;
		Map<String, Object> response = new HashMap<>();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String login = authentication.getPrincipal().toString();
		if(bindingResult.hasErrors()) {
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
		estado.setUsuariologeado(login);
		estadonew =	 estadoservice.save(estado);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El estado ha sido creado con exito");
		response.put("estado", estadonew);
			
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

	}


    @Secured({"ROLE_ADMIN"})
	@PutMapping("/estados/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Estado_imprsora estado,BindingResult result, @PathVariable Long id) {
		
		Estado_imprsora datoactual = estadoservice.findbyid(id);
		Estado_imprsora estadoupd = null;
		Map<String, Object> response = new HashMap<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String login = authentication.getPrincipal().toString();
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
			datoactual.setUsuariologeado(login);
			datoactual.setEstadoimpresora(estado.getEstadoimpresora());
			estadoupd= estadoservice.save(datoactual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El area ha sido actualizada con exito");
		response.put("estado", estadoupd);
			
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	

	}


    @Secured({"ROLE_ADMIN"})
	@DeleteMapping("/estados/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();

		try {
			estadoservice.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El area ha sido eliminada con exito");	
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}


}
