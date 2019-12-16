package com.cibertec.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.cibertec.app.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
}
