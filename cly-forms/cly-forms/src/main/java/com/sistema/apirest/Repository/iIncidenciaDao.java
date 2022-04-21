package com.sistema.apirest.Repository;

import com.sistema.apirest.entity.Incidencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iIncidenciaDao extends JpaRepository <Incidencia, Long> {
    
}
