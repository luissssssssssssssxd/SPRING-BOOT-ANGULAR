package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.Repository.ICentrocostoDao;
import com.sistema.apirest.entity.CentroCosto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ICentroCostoImpl implements ICentroCostoService {

    @Autowired
    private ICentrocostoDao centrocostoDao;

    @Override
    public List<CentroCosto> findAll() {
        return centrocostoDao.findAll();
    }

    @Override
    public CentroCosto save(CentroCosto centroCosto) {
        return centrocostoDao.save(centroCosto);
    }

    @Override
    public void delete(Long id) {
        centrocostoDao.deleteById(id);
        
    }

    @Override
    public CentroCosto findbyid(Long id) {
        return centrocostoDao.findById(id).orElse(null);
    }
    
}
