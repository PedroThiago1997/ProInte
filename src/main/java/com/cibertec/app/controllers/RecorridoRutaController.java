package com.cibertec.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cibertec.app.models.dao.IEmpresaDao;
import com.cibertec.app.models.dao.IRecorridoRutaDao;
import com.cibertec.app.models.dao.IRutaDao;
import com.cibertec.app.models.entity.Empresa;
import com.cibertec.app.models.entity.RecorridoRuta;
import com.cibertec.app.models.entity.Ruta;
import com.cibertec.app.repository.EmpresaRepository;
import com.cibertec.app.repository.RecorridoRutaRepository;
import com.cibertec.app.repository.RutaRepository;

@Controller
@SessionAttributes("recorrido")
public class RecorridoRutaController {
	
	@Autowired(required = true)
	private IRecorridoRutaDao recorridoDao;
	
	
	@Autowired(required = true)
	private RutaRepository repoRuta;
	
	@Autowired(required = true)
	private IRutaDao rutadao;
	
	@Autowired(required = true)
	private RecorridoRutaRepository repoRecorrido;
	
	public List<Ruta> rrecorrido = new ArrayList<Ruta>();

	@RequestMapping(value = "/listarRecorrido", method = RequestMethod.GET)
	public String listarRecorrido(Model model) {
		model.addAttribute("titulo", "Lista de recorrido");
		model.addAttribute("recorrido", recorridoDao.listarRecorrido());
		return "listarRecorrido";
	}
	
	@RequestMapping(value = "/editRecorrido")
	public String crearrecorrido(Map<String, Object> model) {
		RecorridoRuta recorrido = new RecorridoRuta();
		model.put("recorrido", recorrido);
		model.put("ruta", rutadao.listarRuta());
		model.put("titulo", "DATOS DEL RECORRIDO");
		return "editRecorrido";
	}
	@RequestMapping(value = "/editRecorrido/{idRecorrido}")
	public String editar(@PathVariable(value = "idRecorrido") Long id, Map<String, Object> model) {
		RecorridoRuta recorrido = null;
		if (id > 0) {
			
			recorrido = repoRecorrido.findById(id).get();
			
		} else {
			return "redirect:/listarRecorrido";
		}
		model.put("recorrido", recorrido);	
		model.put("titulo", "Editar Recorrido");
		model.put("ruta", repoRuta.findAll());
		
		return "editRecorrido";
	}
	
	@RequestMapping(value = "/editRecorrido", method = RequestMethod.POST)
	public String guardar(@Valid RecorridoRuta recorrido, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Datos del recorrido");
			
			return "editRecorrido";
		}
		repoRecorrido.save(recorrido);
		status.setComplete();
		return "redirect:listarRecorrido";
	}
	
	@RequestMapping(value = "/eliminarRecorrido/{idRecorrido}")
	public String eliminar(@PathVariable(value = "idRecorrido") Long id) {
		if (id>0) {
			repoRecorrido.deleteById(id);
		}
		return "redirect:/listarRecorrido";
	}

}
