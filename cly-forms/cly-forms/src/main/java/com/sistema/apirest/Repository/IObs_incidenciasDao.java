package com.sistema.apirest.Repository;

import java.util.List;

import com.sistema.apirest.entity.Obs_incidencias;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IObs_incidenciasDao extends JpaRepository<Obs_incidencias, Long> {

    @Transactional
    @Query(value = "select  * from obs_incidencias where incidencia_id = ?1 ",nativeQuery = true)
     List<Obs_incidencias> listado(Long id);
    
}
