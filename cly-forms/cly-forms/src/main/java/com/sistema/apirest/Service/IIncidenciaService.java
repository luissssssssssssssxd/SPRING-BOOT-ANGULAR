package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.entity.Incidencia;

public interface IIncidenciaService {

    public List<Incidencia> findAll();

    public Incidencia save(Incidencia incidencia);

    public void delete(Long id);

    public Incidencia findByid( Long id);
    
}
