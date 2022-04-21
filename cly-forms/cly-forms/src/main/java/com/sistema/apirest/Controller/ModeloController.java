package com.sistema.apirest.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.sistema.apirest.Service.IModeloService;
import com.sistema.apirest.entity.Modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ModeloController {

    @Autowired
    private IModeloService modeloService;

    @GetMapping("/modelos")
    public List<Modelo> listar(){
        return modeloService.findAll();
    }


    //METODO GET ID
    @Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/modelos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Modelo  modelo = null;
		Map<String, Object> response = new HashMap<>();

		try {
		modelo = modeloService.findbyid(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return  new  ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
//		Si registro buscado en NULO
		if(modelo== null) {
			response.put("mensaje", "El modelo con el  ID:  ".concat(id.toString().concat(" No existe en la BD")));
			return  new  ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		return  new  ResponseEntity<>(modelo,HttpStatus.OK);
	}

    //METODO POST

    @Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/modelos")
	public ResponseEntity<?> create(@Valid  @RequestBody Modelo modelo,BindingResult bindingResult) {
		Modelo  modelonew = null;
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
		modelonew =	 modeloService.save(modelo);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El area ha sido creado con exito");
		response.put("area", modelonew);
			
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

	}

    //METODO PUT

    @Secured({"ROLE_ADMIN"})
	@PutMapping("/modelos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Modelo modelo,BindingResult result, @PathVariable Long id) {
		
		Modelo datoactual = modeloService.findbyid(id);
		Modelo modeloupd = null;
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
			datoactual.setModelo(modelo.getModelo());
			modeloupd= modeloService.save(datoactual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El modelo ha sido actualizada con exito");
		response.put("modelo", modeloupd);
			
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	
	}

    //METODO DELETE

    @Secured({"ROLE_ADMIN"})
	@DeleteMapping("/modelos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();

		try {
			modeloService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El modelo ha sido eliminada con exito");	
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}






    
}
