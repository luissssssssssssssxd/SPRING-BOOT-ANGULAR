package com.sistema.apirest.Controller;

import com.sistema.apirest.Service.IAreaService;
import com.sistema.apirest.Service.ICentroCostoService;
import com.sistema.apirest.Service.IEstadoService;
import com.sistema.apirest.Service.IImpresoraService;
import com.sistema.apirest.Service.IMarcaService;
import com.sistema.apirest.Service.IModeloService;
import com.sistema.apirest.Service.IPisoService;
import com.sistema.apirest.Service.ISociedadaService;
import com.sistema.apirest.Service.IUbicacionService;

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

import com.sistema.apirest.entity.Area;
import com.sistema.apirest.entity.CentroCosto;
import com.sistema.apirest.entity.Estado_imprsora;
import com.sistema.apirest.entity.Impresora;
import com.sistema.apirest.entity.Marca;
import com.sistema.apirest.entity.Modelo;
import com.sistema.apirest.entity.Piso;
import com.sistema.apirest.entity.Sociedades;
import com.sistema.apirest.entity.Ubicacion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class ImpresoraController {

    @Autowired
    private IImpresoraService impresoraService;
    @Autowired
    private IEstadoService estadoService;
    @Autowired
    private IMarcaService marcaService;
    @Autowired
    private IAreaService areaService;
    @Autowired
    private IModeloService modeloService;
	@Autowired
	private IPisoService iPisoService;
	@Autowired
	private ISociedadaService iSociedadaService;
	@Autowired
	private ICentroCostoService centroCostoService;
	@Autowired 
	private IUbicacionService iUbicacionService;

    @GetMapping("/impresoras")
    public List<Impresora> listar(){
        return impresoraService.findAll();
    }

    @GetMapping("/impresoras/impresoras-estados")
    public List<Estado_imprsora> listarEstados(){
        return estadoService.findAll();
    }
	@GetMapping("/impresoras/impresoras-pisos")
    public List<Piso> listarpisos(){
        return iPisoService.findAll();
    }
	@GetMapping("/impresoras/impresoras-sociedad")
    public List<Sociedades> listarsociedad(){
        return iSociedadaService.findAll();
    }
	@GetMapping("/impresoras/impresoras-centrocosto")
    public List<CentroCosto> listarcentrocosto(){
        return centroCostoService.findAll();
    }
	@GetMapping("/impresoras/impresoras-ubicacion")
    public List<Ubicacion> listarubicacion(){
        return iUbicacionService.findAll();
    }
    @GetMapping("/impresoras/impresoras-marcas")
    public List<Marca> listarMarcas(){
        return marcaService.getMarcas();
    }
    @GetMapping("/impresoras/impresoras-modelos")
    public List<Modelo> listarModelos(){
        return modeloService.findAll();
    }
    @GetMapping("/impresoras/impresoras-areas")
    public List<Area> listarAreas(){
        return areaService.findAll();
    }
	
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/impresoras/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Impresora impresora = null;
        Map<String, Object> response = new HashMap<>();


        try {
            impresora = impresoraService.findbyid(id);
    
            } catch (DataAccessException e) {
                response.put("mensaje", "Error al realizar la consulta en la BD");
                response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return  new  ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            
            
    //		Si registro buscado en NULO
            if(impresora == null) {
                response.put("mensaje", "La impresora con el ID:  ".concat(id.toString().concat(" No existe en la BD")));
                return  new  ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
            
            return  new  ResponseEntity<>(impresora,HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/impresoras")
	public ResponseEntity<?> create(@Valid  @RequestBody Impresora id,BindingResult bindingResult) {
		Impresora  impresoranew = null;
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
		impresoranew =	 impresoraService.save(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La impresora ha sido creado con exito");
		response.put("estado", impresoranew);
			
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

	}

    @Secured({"ROLE_ADMIN"})
	@PutMapping("/impresoras/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Impresora impresora,BindingResult result, @PathVariable Long id) {
		
		Impresora datoactual = impresoraService.findbyid(id);
		Impresora marcaupd = null;
		Impresora estadoupd = null;
		Impresora modeloupd = null;
		Impresora numero_serieupd = null;
		Impresora fecha_movupd = null;
		Impresora obsupd = null;
		Impresora areaupd = null;
        
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
			response.put("mensaje", "Error: no se pudo editar, la impresora con el numero de serie: ".concat(id.toString().concat(" No existe en la BD")));
			return  new  ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		try {
			datoactual.setMarca(impresora.getMarca());
			marcaupd = impresoraService.save(datoactual);
			datoactual.setEstado(impresora.getEstado());
			estadoupd = impresoraService.save(datoactual);
			datoactual.setModelo(impresora.getModelo());
			modeloupd = impresoraService.save(datoactual);
			datoactual.setNumeroserie(impresora.getNumeroserie());
			numero_serieupd = impresoraService.save(datoactual);
			datoactual.setObservacion(impresora.getObservacion());
			obsupd = impresoraService.save(datoactual);
			datoactual.setArea(impresora.getArea());
			areaupd = impresoraService.save(datoactual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "La impresora ha sido actualizada con exito");
		response.put("marca", marcaupd);
		response.put("area", areaupd);
		response.put("modelo", modeloupd);
		response.put("observacion", obsupd);
		response.put("Numero de serie", numero_serieupd);
		response.put("estado", estadoupd);
			
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	

	}

    @Secured({"ROLE_ADMIN"})
	@DeleteMapping("/impresoras/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();

		try {
			impresoraService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la BD");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "La impresora ha sido eliminada con exito");	
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}


    
}
