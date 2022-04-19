package com.sistema.apirest.Service;

import com.sistema.apirest.entity.Impresora;

import java.util.List;


public interface IImpresoraService {


    public List<Impresora> findAll();

	public Impresora save(Impresora impresora); 

	public void delete(String numeroserie); 

	public Impresora findbyid(String numeroserie);


    
}
