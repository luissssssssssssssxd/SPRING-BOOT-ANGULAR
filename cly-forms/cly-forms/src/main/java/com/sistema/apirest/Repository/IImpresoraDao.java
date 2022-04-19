package com.sistema.apirest.Repository;

import com.sistema.apirest.entity.Impresora;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface IImpresoraDao extends JpaRepository<Impresora,String> {
    
}
