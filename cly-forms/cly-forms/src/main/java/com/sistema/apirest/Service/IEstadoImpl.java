package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.Repository.iEstadoDao;
import com.sistema.apirest.entity.Estado_imprsora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IEstadoImpl implements IEstadoService {


    @Autowired
    private iEstadoDao idao;

    @Override
    public List<Estado_imprsora> findAll() {
        return idao.findAll();
    }

    @Override
    public Estado_imprsora save(Estado_imprsora estado) {
        return idao.save(estado);
    }

    @Override
    public void delete(Long id) {
        idao.deleteById(id);  
        
    }

    @Override
    public Estado_imprsora findbyid(Long id) {
        return idao.findById(id).orElse(null);
    }
    
    
}
