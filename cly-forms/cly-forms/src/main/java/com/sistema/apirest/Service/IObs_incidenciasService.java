package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.entity.Obs_incidencias;

public interface IObs_incidenciasService {

    public List<Obs_incidencias> findAll();

    public Obs_incidencias save(Obs_incidencias obs_incidencia);

    public void delete(Long id);

    public Obs_incidencias findByid(Long id);
    
}
