package com.cibertec.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.app.models.entity.PreguntasFrecuente;

@Repository
public class PreguntaDaoImpl implements IPreguntaDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<PreguntasFrecuente> findAll() {
		return em.createQuery("from PreguntasFrecuente").getResultList();
	}

	@Override

	@Transactional(readOnly = true)
	public PreguntasFrecuente findOne(Long id) {
		return em.find(PreguntasFrecuente.class, id);
	}

	@Override
	@Transactional
	public void save(PreguntasFrecuente pregunta) {
		if (pregunta.getIdpregunta() != null && pregunta.getIdpregunta() > 0) {
			em.merge(pregunta);
		} else {
			em.persist(pregunta);
		}
	}

	@Override
	@Transactional
	public void eliminar(Long id) {
		em.remove(findOne(id));

	}

}
