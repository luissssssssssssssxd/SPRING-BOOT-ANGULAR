package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.Repository.ISociedadDao;
import com.sistema.apirest.entity.Sociedades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ISociedadImpl implements ISociedadaService {

    @Autowired
    private ISociedadDao  iSociedadDao;

    @Override
    public List<Sociedades> findAll() {
        return iSociedadDao.findAll();
    }

    @Override
    public Sociedades save(Sociedades sociedades) {
        return iSociedadDao.save(sociedades);
    }

    @Override
    public void delete(Long id) {
        iSociedadDao.deleteById(id);
        
    }

    @Override
    public Sociedades findbyid(Long id) {
        return iSociedadDao.findById(id).orElse(null);
    }
    
}
