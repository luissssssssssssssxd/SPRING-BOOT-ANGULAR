package com.sistema.apirest.Repository;

import org.springframework.data.repository.CrudRepository;

import com.sistema.apirest.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
	
	
	public Usuario findByUsername(String username);

	

}
