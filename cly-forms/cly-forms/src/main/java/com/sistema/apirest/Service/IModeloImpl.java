package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.Repository.iModeloDao;
import com.sistema.apirest.entity.Modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IModeloImpl implements IModeloService{


    @Autowired
    private iModeloDao idao;

    @Override
    public List<Modelo> findAll() {
        return idao.findAll();
    }

    @Override
    public Modelo save(Modelo area) {
        return idao.save(area);
    }

    @Override
    public void delete(Long id) {
    idao.deleteById(id);        
    }

    @Override
    public Modelo findbyid(Long id) {
        return idao.findById(id).orElse(null);
    }


    
}
