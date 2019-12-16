package com.cibertec.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.app.models.entity.Usuario;
import com.cibertec.app.repository.IUsuarioDao;

@Service
public class UsuarioServiceImpls implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findOne(Long id) {
		// TODO Auto-generated method stub
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario loginUsuario(String correo, String password) {
		Usuario user =  new Usuario();
		try {
			String query = "Select u from Usuario u where u.correo=:correo and u.password=:password";
			Query q = em.createQuery(query);
			
			q.setParameter("correo", correo);
			q.setParameter("password", password);
			
			user = (Usuario)q.getSingleResult();
			
			System.out.println(user);
			
			return user;
			
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

}
