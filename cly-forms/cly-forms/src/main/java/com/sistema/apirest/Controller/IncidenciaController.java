package com.sistema.apirest.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.sistema.apirest.Service.IImpresoraService;
import com.sistema.apirest.Service.IIncidenciaService;
import com.sistema.apirest.entity.Impresora;
import com.sistema.apirest.entity.Incidencia;

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
public class IncidenciaController {


    @Autowired
    private IIncidenciaService incidenciaService;
    

    @Autowired
    private IImpresoraService impresoraService;

    @GetMapping("/incidencias")
    public List<Incidencia> listarIncidencias(){
        return incidenciaService.findAll();
    }

    @GetMapping("/incidencias/incidencias-impresoras")
    public List<Impresora> listarImpresoras(){
        return impresoraService.findAll();
    }


    // METODO GET ID INCIDENCIA
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/incidencias/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Incidencia incidencia = null;
        Map<String, Object> response = new HashMap<>();


        try {
            incidencia = incidenciaService.findByid(id);
    
            } catch (DataAccessException e) {
                response.put("mensaje", "Error al realizar la consulta en la BD");
                response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return  new  ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            
            
    //		Si registro buscado en NULO
            if(incidencia == null) {
                response.put("mensaje", "La incidencia con el ID:  ".concat(id.toString().concat(" No existe en la BD")));
                return  new  ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
            
            return  new  ResponseEntity<>(incidencia,HttpStatus.OK);
    }

    // METODO POST IMPRESORAS

    @Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/incidencias")
	public ResponseEntity<?> create(@Valid  @RequestBody Incidencia id,BindingResult bindingResult) {
		Incidencia  incidencianew = null;
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
		incidencianew =	 incidenciaService.save(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La incidencia ha sido creado con exito");
		response.put("incidencia", incidencianew);
			
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

	}



    // METODO PUT INCIDENCIAS

    @Secured({"ROLE_ADMIN"})
	@PutMapping("/incidencias/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Incidencia incidencia,BindingResult result, @PathVariable Long id) {
		
		Incidencia datoactual = incidenciaService.findByid(id);
		Incidencia fecha_inicioupd = null;
		Incidencia fecha_finupd = null;
		Incidencia impresoraudp = null;
        
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
			response.put("mensaje", "Error: no se pudo editar la incidencia con el id: ".concat(id.toString().concat(" No existe en la BD")));
			return  new  ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		try {
			datoactual.setFecha_inicio(incidencia.getFecha_inicio());
			datoactual.setFecha_fin(incidencia.getFecha_fin());
			datoactual.setImpresora(incidencia.getImpresora());
			impresoraudp = incidenciaService.save(datoactual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "La incidencia ha sido actualizada con exito");
		response.put("fecha inicio", fecha_inicioupd);
		response.put("fecha fin", fecha_finupd);
			
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	

	}

    @Secured({"ROLE_ADMIN"})
	@DeleteMapping("/incidencias/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();

		try {
			incidenciaService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "La incidencia ha sido eliminada con exito");	
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}




}
