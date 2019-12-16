package com.cibertec.app.models.dao;

import java.util.List;


import com.cibertec.app.models.entity.Ruta;

public interface IRutaDao {
	
	public List<Ruta> listarRuta();

	public void registarRuta(Ruta ruta);

	public Ruta editarRuta(Long id);
	
	public void eliminarRuta(Long id);

}
