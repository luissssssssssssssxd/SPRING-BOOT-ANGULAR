package com.sistema.apirest.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.apirest.Repository.IImpresoraDao;
import com.sistema.apirest.entity.Impresora;


@Service
public class IImpresoraImpl implements IImpresoraService {

    @Autowired
    private IImpresoraDao iDao;

    @Override
    public List<Impresora> findAll(){
        return iDao.findAll();
    }

    @Override
    public Impresora save(Impresora impresora) {
        return iDao.save(impresora);
    }

    @Override
    public void delete(Long numeroserie) {
        iDao.deleteById(numeroserie);  
        
    }

    @Override
    public Impresora findbyid(Long numeroserie) {
        return iDao.findById(numeroserie).orElse(null);
    }




    
}
