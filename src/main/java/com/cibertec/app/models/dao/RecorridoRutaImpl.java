package com.cibertec.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.app.models.entity.RecorridoRuta;

@Repository
public class RecorridoRutaImpl implements IRecorridoRutaDao{

	
	@PersistenceContext
	private EntityManager recor;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<RecorridoRuta> listarRecorrido() {
		return recor.createQuery("from RecorridoRuta").getResultList();
	}

	@Override
	public void registarRecorrido(RecorridoRuta ruta) {
		if (ruta.getIdRecorrido() != null && ruta.getIdRecorrido() > 0) {
			recor.merge(ruta);
		} else {
			recor.persist(ruta);
		}
		
	}

	@Override
	public RecorridoRuta editarRecorrido(Long id) {
		
		
		return recor.find(RecorridoRuta.class, id);
	}

	
	@Override
	public void eliminarRecorrido(Long id) {
		recor.remove(editarRecorrido(id));
		
	}

	@Override
	public RecorridoRuta buscarRelacion(Long idRuta) {
		
		RecorridoRuta direccion =  new RecorridoRuta();
		try {
			String query = "Select u from Recorrido u where u.id_ruta =:idRuta";
			Query q = recor.createQuery(query);
			
			q.setParameter("idRuta", idRuta);
			
			direccion = (RecorridoRuta)q.getSingleResult();
			
			return direccion;
			
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

}
