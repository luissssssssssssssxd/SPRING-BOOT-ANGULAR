package com.sistema.apirest.Repository;

import com.sistema.apirest.entity.CentroCosto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICentrocostoDao extends JpaRepository<CentroCosto,Long> {
    
}
