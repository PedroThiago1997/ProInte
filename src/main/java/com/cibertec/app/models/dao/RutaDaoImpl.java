package com.cibertec.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cibertec.app.models.entity.Ruta;

@Repository
public class RutaDaoImpl implements IRutaDao{

	
	@PersistenceContext
	private EntityManager rut;
	
	@Override
	public List<Ruta> listarRuta() {
		return rut.createQuery("from Ruta").getResultList();
	}

	@Override
	public void registarRuta(Ruta ruta) {
		if (ruta.getIdruta() != null && ruta.getIdruta() > 0) {
			rut.merge(ruta);
		} else {
			rut.persist(ruta);
		}
		
	}

	@Override
	public Ruta editarRuta(Long id) {
		return rut.find(Ruta.class, id);
	}

	@Override
	public void eliminarRuta(Long id) {
		rut.remove(editarRuta(id));
		
	}

}
