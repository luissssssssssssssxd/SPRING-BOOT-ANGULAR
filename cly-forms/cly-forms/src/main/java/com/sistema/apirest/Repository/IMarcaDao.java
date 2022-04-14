package com.sistema.apirest.Repository;

import com.sistema.apirest.entity.Marca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMarcaDao extends JpaRepository<Marca,Long> {
    
}
