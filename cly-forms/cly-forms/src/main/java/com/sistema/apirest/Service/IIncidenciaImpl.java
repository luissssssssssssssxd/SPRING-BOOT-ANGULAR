package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.Repository.iIncidenciaDao;
import com.sistema.apirest.entity.Incidencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IIncidenciaImpl implements IIncidenciaService{

    @Autowired
    private iIncidenciaDao iDao;

    @Override
    public List<Incidencia> findAll(){
        return iDao.findAll();
    }

    @Override
    public Incidencia save(Incidencia incidencia) {
        return iDao.save(incidencia);
    }

    @Override
    public void delete(Long id) {
        iDao.deleteById(id);  
        
    }

    @Override
    public Incidencia findByid(Long id) {
        return iDao.findById(id).orElse(null);
    }

    
}
