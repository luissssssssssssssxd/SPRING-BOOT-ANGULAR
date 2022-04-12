package com.sistema.apirest.Repository;

import com.sistema.apirest.entity.Area;

import org.springframework.data.jpa.repository.JpaRepository;

public interface iAreaDao extends JpaRepository<Area,Long> {
    
}
