package com.cibertec.app.models.dao;

import java.util.List;

import com.cibertec.app.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();

	public void save(Usuario usuario);
	
	public Usuario findOne(Long id);
	
	public void delete(Long id);
	
	public Usuario loginUsuario(String correo, String password);
	
}
