package com.sistema.apirest.Repository;

import com.sistema.apirest.entity.Sociedades;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ISociedadDao extends JpaRepository<Sociedades,Long> {
    
}
