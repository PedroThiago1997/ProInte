package com.cibertec.app.models.dao;

import java.util.List;

import com.cibertec.app.models.entity.PreguntasFrecuente;

public interface IPreguntaDao {

	public List<PreguntasFrecuente> findAll();

	public void save(PreguntasFrecuente pregunta);

	public PreguntasFrecuente findOne(Long id);
	
	public void eliminar(Long id);

}
