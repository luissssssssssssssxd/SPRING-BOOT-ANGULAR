package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.Repository.IUbicacionDao;
import com.sistema.apirest.entity.Ubicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUbicacionImpl implements IUbicacionService {

    @Autowired
    private IUbicacionDao dao;

    @Override
    public List<Ubicacion> findAll() {
        return dao.findAll();
    }

    @Override
    public Ubicacion save(Ubicacion ubicacion) {
        return dao.save(ubicacion);
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
        
    }

    @Override
    public Ubicacion findbyid(Long id) {
        return dao.findById(id).orElse(null);
    }
    
}
