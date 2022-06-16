package com.sistema.apirest.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.sistema.apirest.Repository.IObs_incidenciasDao;
import com.sistema.apirest.Service.IIncidenciaService;
import com.sistema.apirest.Service.IObs_incidenciasService;
import com.sistema.apirest.entity.Incidencia;
import com.sistema.apirest.entity.Obs_incidencias;

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
public class Obs_incidenciasController {


    @Autowired
    private IObs_incidenciasService obs_incidenciasService;

    @Autowired
    private IIncidenciaService incidenciaService;

	@Autowired
	private IObs_incidenciasDao dao;

    @GetMapping("/obs_incidencias")
    public List<Obs_incidencias> listarObs_incidencias(){
        return obs_incidenciasService.findAll();
    }
    @GetMapping("/obs_incidencias/obs_incidencias-incidencias")
    public List<Incidencia> listarIncidencias(){
        return incidenciaService.findAll();
    }

 /*    // METODO GET OBS POR ID
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/obs_incidencias/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Obs_incidencias obs_incidencias = null;
        Map<String, Object> response = new HashMap<>();


        try {
            obs_incidencias = obs_incidenciasService.findByid(id);
    
            } catch (DataAccessException e) {
                response.put("mensaje", "Error al realizar la consulta en la BD");
                response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return  new  ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            
            
    //		Si registro buscado en NULO
            if(obs_incidencias == null) {
                response.put("mensaje", "La impresora con el ID:  ".concat(id.toString().concat(" No existe en la BD")));
                return  new  ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
            
            return  new  ResponseEntity<>(obs_incidencias,HttpStatus.OK);
    } */

	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/obs_incidencias/{id}")
    public List<Obs_incidencias> showCredential(@PathVariable Long id) {
        return this.dao.listado(id);
    }

	  

    // METODO POST OBS
    @Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/obs_incidencias")
	public ResponseEntity<?> create(@Valid  @RequestBody Obs_incidencias id,BindingResult bindingResult) {
		Obs_incidencias  obs_incidenciasnew = null;
		Map<String, Object> response = new HashMap<>();
		

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
            obs_incidenciasnew =	 obs_incidenciasService.save(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La impresora ha sido creado con exito");
		response.put("obs", obs_incidenciasnew);
			
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

	}

    //METODO PUT OBS
    @Secured({"ROLE_ADMIN"})
	@PutMapping("/obs_incidencias/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Obs_incidencias obs_incidencias,BindingResult result, @PathVariable Long id) {
		
		Obs_incidencias datoactual = obs_incidenciasService.findByid(id);
		Obs_incidencias obsupd = null;
        

        
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
			response.put("mensaje", "Error: no se pudo editar la obs con el id: ".concat(id.toString().concat(" No existe en la BD")));
			return  new  ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		try {
			datoactual.setObservacion(obs_incidencias.getObservacion());
			obsupd = obs_incidenciasService.save(datoactual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "La observacion ha sido actualizada con exito");
		response.put("obs", obsupd);
			
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	

	}


    // METODO DELETE OBS
    @Secured({"ROLE_ADMIN"})
	@DeleteMapping("/obs_incidencias/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();

		try {
			obs_incidenciasService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "La obs ha sido eliminada con exito");	
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}



    
}
