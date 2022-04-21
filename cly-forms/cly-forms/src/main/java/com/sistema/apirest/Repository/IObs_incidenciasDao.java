package com.sistema.apirest.Repository;

import com.sistema.apirest.entity.Obs_incidencias;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IObs_incidenciasDao extends JpaRepository<Obs_incidencias, Long> {
    
}
