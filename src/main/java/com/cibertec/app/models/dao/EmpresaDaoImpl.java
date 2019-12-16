package com.cibertec.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.app.models.entity.Empresa;

@Repository
public class EmpresaDaoImpl implements IEmpresaDao{
	
	@PersistenceContext
	private EntityManager emp;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Empresa> listarEmpresa() {
		
		return emp.createQuery("from Empresa").getResultList();
	}

	@Override
	@Transactional
	public void guardarEmpresa(Empresa empresa) {
		if (empresa.getIdempresa() != null && empresa.getIdempresa() > 0) {
			emp.merge(empresa);
		} else {
			emp.persist(empresa);
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Empresa editarEmpresa(Long id) {
		return emp.find(Empresa.class, id);
	}

	@Override
	@Transactional
	public void eliminarEmpresa(Long id) {
		emp.remove(editarEmpresa(id));
		
	}

}
