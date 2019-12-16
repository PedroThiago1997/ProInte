package com.cibertec.app.controllers;

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

import com.cibertec.app.models.dao.IPreguntaDao;
import com.cibertec.app.models.entity.PreguntasFrecuente;
import com.cibertec.app.repository.PreguntasFrecuentesRepository;

@Controller
@SessionAttributes("pregunta")
public class PreguntaController {

	@Autowired
	private IPreguntaDao preguntaDao;
	
	@Autowired
	private PreguntasFrecuentesRepository repoPreguntas;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Preguntas");
		model.addAttribute("preguntas", preguntaDao.findAll());
		return "listar";
	}

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		PreguntasFrecuente pregunta = new PreguntasFrecuente();
		model.put("pregunta", pregunta);
		model.put("titulo", "Formulario de Preguntas");
		return "form";
	}

	@RequestMapping(value = "/form/{idpregunta}")
	public String editar(@PathVariable(value = "idpregunta") Long id, Map<String, Object> model) {
		PreguntasFrecuente pregunta = null;
		if (id > 0) {
			pregunta = preguntaDao.findOne(id);
		} else {
			return "redirect:/listar";
		}
		model.put("pregunta", pregunta);
		model.put("titulo", "Editar Pregunta");
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid PreguntasFrecuente pregunta, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Pregunta");
			return "form";
		}
		repoPreguntas.save(pregunta);
		//preguntaDao.save(pregunta);
		status.setComplete();
		return "redirect:listar";
	}
	
	@RequestMapping(value = "/eliminar/{idpregunta}")
	public String eliminar(@PathVariable(value = "idpregunta") Long id) {
		if (id>0) {
			preguntaDao.eliminar(id);
		}
		return "redirect:/listar";
	}

}
