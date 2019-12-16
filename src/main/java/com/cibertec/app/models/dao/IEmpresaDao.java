package com.cibertec.app.models.dao;

import java.util.List;

import com.cibertec.app.models.entity.Empresa;


public interface IEmpresaDao {
	
	public List<Empresa> listarEmpresa();

	public void guardarEmpresa(Empresa empresa);

	public Empresa editarEmpresa(Long id);
	
	public void eliminarEmpresa(Long id);

}
