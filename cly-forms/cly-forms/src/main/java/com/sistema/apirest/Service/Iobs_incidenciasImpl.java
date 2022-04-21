package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.Repository.IObs_incidenciasDao;
import com.sistema.apirest.entity.Obs_incidencias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Iobs_incidenciasImpl  implements IObs_incidenciasService{

    @Autowired
    private IObs_incidenciasDao iDao;

    @Override
    public List<Obs_incidencias> findAll(){
        return iDao.findAll();
    }

    @Override
    public Obs_incidencias save(Obs_incidencias obs_incidencias) {
        return iDao.save(obs_incidencias);
    }

    @Override
    public void delete(Long id) {
        iDao.deleteById(id);  
        
    }

    @Override
    public Obs_incidencias findByid(Long id) {
        return iDao.findById(id).orElse(null);
    }





    
}
