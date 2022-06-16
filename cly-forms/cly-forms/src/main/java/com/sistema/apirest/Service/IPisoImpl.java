package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.Repository.IPisoDao;
import com.sistema.apirest.entity.Piso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IPisoImpl implements IPisoService {

    @Autowired
    private IPisoDao dao;

    @Override
    public List<Piso> findAll() {
        return dao.findAll();
    }

    @Override
    public Piso save(Piso piso) {
        return dao.save(piso);
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
        
    }

    @Override
    public Piso findbyid(Long id) {
        return dao.findById(id).orElse(null);
    }
    
}
