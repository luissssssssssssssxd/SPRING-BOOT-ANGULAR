package com.sistema.apirest.Service;

import java.util.List;

import com.sistema.apirest.entity.Area;

public interface IAreaService {

    public List<Area> findAll();
	
	public Area save(Area area);
	
	public void delete(Long id);
	
	public Area findbyid(Long id);
    
}
