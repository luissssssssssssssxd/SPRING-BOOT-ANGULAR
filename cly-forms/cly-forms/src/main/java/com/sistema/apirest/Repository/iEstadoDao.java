package com.sistema.apirest.Repository;

import com.sistema.apirest.entity.Estado_imprsora;

import org.springframework.data.jpa.repository.JpaRepository;

public interface iEstadoDao extends JpaRepository<Estado_imprsora, Long> {
    
}
