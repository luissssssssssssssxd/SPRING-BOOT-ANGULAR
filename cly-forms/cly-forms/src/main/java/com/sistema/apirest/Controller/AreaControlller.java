package com.sistema.apirest.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.sistema.apirest.Repository.IUsuarioDao;
import com.sistema.apirest.Service.IAreaService;
import com.sistema.apirest.entity.Area;
import com.sistema.apirest.entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
public class AreaControlller {

    @Autowired
    private IAreaService areaservice;
	@Autowired
	private IUsuarioDao usuariodao;

	@GetMapping("/areas")
    public List<Area> listar() {

        return areaservice.findAll();

    }

    	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/areas/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Area  area = null;
		Map<String, Object> response = new HashMap<>();

		try {
		area = areaservice.findbyid(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return  new  ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
//		Si registro buscado en NULO
		if(area == null) {
			response.put("mensaje", "El area  ID:  ".concat(id.toString().concat(" No existe en la BD")));
			return  new  ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		return  new  ResponseEntity<>(area,HttpStatus.OK);
	}


    @Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/areas")
	public ResponseEntity<?> create(@Valid  @RequestBody Area area,BindingResult bindingResult) {
		Area  areanew = null;
		Map<String, Object> response = new HashMap<>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String login = authentication.getPrincipal().toString();

		Usuario  usuario =  usuariodao.findByUsername(login);

		System.out.println("el usuario logeado es \n"+ login);
		System.out.println("este deberia ser el usuario buscado \n"+usuario);

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
		area.setUsuariologeado(login);
	
		areanew =	 areaservice.save(area);
		System.out.println(areanew);	
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El area ha sido creado con exito");
		response.put("area", areanew);
			
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

	}


    @Secured({"ROLE_ADMIN"})
	@PutMapping("/areas/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Area area,BindingResult result, @PathVariable Long id) {
		
		Area datoactual = areaservice.findbyid(id);
		Area areaupd = null;
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
			datoactual.setNombrearea(area.getNombrearea());
			datoactual.setUsuariologeado(login);
			areaupd= areaservice.save(datoactual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El area ha sido actualizada con exito");
		response.put("area", areaupd);
			
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	

	}


    @Secured({"ROLE_ADMIN"})
	@DeleteMapping("/areas/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();

		try {
			areaservice.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El area ha sido eliminada con exito");	
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}


}
