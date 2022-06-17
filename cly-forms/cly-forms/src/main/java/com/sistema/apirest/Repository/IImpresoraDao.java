package com.sistema.apirest.Repository;

import com.sistema.apirest.entity.Impresora;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




@Repository
public interface IImpresoraDao extends JpaRepository<Impresora,Long> {

    @Transactional(readOnly = true)
    @Query(value="SELECT * FROM  impresora INNER JOIN estados_impresoras on estados_impresoras.id = impresora.estado_id WHERE estados_impresoras.estadoimpresora = 'ACTIVO' ",nativeQuery = true)
    List<Impresora> impresorasactivas();

    @Transactional
    @Modifying
    @Query("UPDATE Impresora i INNER JOIN Incidencias in ON i.id= in.impresora_id  INNER JOIN Estado_imprsora est ON i.estado_id = est.id  SET i.estado_id=?2  WHERE in.id=?1")
    void updatestatusprinter(Long id, Long status);
    
}
