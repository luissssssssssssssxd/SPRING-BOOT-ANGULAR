package com.sistema.apirest.Repository;

import com.sistema.apirest.entity.Modelo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface iModeloDao extends JpaRepository <Modelo, Long> {
    
}
