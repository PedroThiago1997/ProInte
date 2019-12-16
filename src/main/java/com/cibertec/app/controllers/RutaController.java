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
import com.cibertec.app.models.dao.IRutaDao;
import com.cibertec.app.models.dao.RecorridoRutaImpl;
import com.cibertec.app.models.entity.Empresa;
import com.cibertec.app.models.entity.RecorridoRuta;
import com.cibertec.app.models.entity.Ruta;
import com.cibertec.app.repository.EmpresaRepository;
import com.cibertec.app.repository.RecorridoRutaRepository;
import com.cibertec.app.repository.RutaRepository;

@Controller
@SessionAttributes("ruta")
public class RutaController {

	@Autowired(required = true)
	private IRutaDao rutaDao;

	@Autowired(required = true)
	private EmpresaRepository repoEmpresa;

	@Autowired(required = true)

	private IEmpresaDao empresaDao;

	@Autowired(required = true)
	private RutaRepository repoRuta;

	@Autowired
	private RecorridoRutaRepository repoRecorrido;

	public List<Empresa> empresas = new ArrayList<Empresa>();

	@RequestMapping(value = "/listarRutas", method = RequestMethod.GET)
	public String listarEmpre(Model model) {
		model.addAttribute("titulo", "Listado Rutas");
		model.addAttribute("rutas", repoRuta.findAll());
		return "listarRutas";
	}

	@RequestMapping(value = "/editRutas")
	public String crearuta(Map<String, Object> model) {
		Ruta ruta = new Ruta();
		model.put("ruta", ruta);
		model.put("empresas", empresaDao.listarEmpresa());
		model.put("titulo", "DATOS DE LA RUTA");
		return "editRutas";
	}

	@RequestMapping(value = "/editRutas/{idruta}")
	public String editar(@PathVariable(value = "idruta") Long id, Map<String, Object> model) {
		Ruta rutas = null;
		if (id > 0) {

			rutas = repoRuta.findById(id).get();

		} else {
			return "redirect:/listarRutas";
		}
		model.put("ruta", rutas);
		model.put("titulo", "Editar Empresa");
		model.put("empresas", repoEmpresa.findAll());

		return "editRutas";
	}

	@RequestMapping(value = "/editRutas", method = RequestMethod.POST)
	public String guardar(@Valid Ruta ruta, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Datos de la empresa");

			return "editRutas";
		}

		repoRuta.save(ruta);
		status.setComplete();
		return "redirect:/listarRutas";
	}

	@RequestMapping(value = "/eliminarRuta/{idruta}")
	public String eliminar(@PathVariable(value = "idruta") Long id) {
		if (id > 0) {
			RecorridoRutaImpl reco = new RecorridoRutaImpl();
			RecorridoRuta rec = reco.buscarRelacion(id);
			if (rec != null) {
				return "redirect:/listarRutas";
			} else {
				repoRuta.deleteById(id);
			}
		}
		return "redirect:/listarRutas";
	}

}
