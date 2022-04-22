package com.sistema.apirest.Repository;

import com.sistema.apirest.entity.Piso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPisoDao extends JpaRepository<Piso,Long>{
    
}
