package com.sistema.apirest.Repository;

import com.sistema.apirest.entity.Ubicacion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUbicacionDao extends JpaRepository<Ubicacion,Long> {
    
}
