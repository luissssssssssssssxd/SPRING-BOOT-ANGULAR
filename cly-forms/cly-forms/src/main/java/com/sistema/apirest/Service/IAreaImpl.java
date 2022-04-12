package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.Repository.iAreaDao;
import com.sistema.apirest.entity.Area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class IAreaImpl implements IAreaService {

    @Autowired
    private iAreaDao idao;

    @Override
    public List<Area> findAll() {
        return idao.findAll();
    }

    @Override
    public Area save(Area area) {
        return idao.save(area);
    }

    @Override
    public void delete(Long id) {
    idao.deleteById(id);        
    }

    @Override
    public Area findbyid(Long id) {
        return idao.findById(id).orElse(null);
    }
    
}
