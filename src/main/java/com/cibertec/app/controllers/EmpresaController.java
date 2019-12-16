

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

import com.cibertec.app.models.dao.IEmpresaDao;
import com.cibertec.app.models.entity.Empresa;

@Controller
@SessionAttributes("empresa")
public class EmpresaController {

	
	@Autowired
	private IEmpresaDao empresaDao;

	@RequestMapping(value = "/empresa", method = RequestMethod.GET)
	public String listarEmpre(Model model) {
		model.addAttribute("titulo", "Listado de Empresas");
		model.addAttribute("empresas", empresaDao.listarEmpresa());
		return "empresa";
	}
	
	@RequestMapping(value = "/empresaEdit")
	public String crearempresa(Map<String, Object> model) {
		Empresa empresa = new Empresa();
		model.put("empresa", empresa);
		model.put("titulo", "DATOS DE LA EMPRESA");
		return "empresaEdit";
	}
	
	@RequestMapping(value = "/empresaEdit/{idempresa}")
	public String editar(@PathVariable(value = "idempresa") Long id, Map<String, Object> model) {
		Empresa empresa = null;
		if (id > 0) {
			empresa = empresaDao.editarEmpresa(id);
		} else {
			return "redirect:/empresa";
		}
		model.put("empresa", empresa);
		model.put("titulo", "Editar Empresa");
		return "empresaEdit";
	}
	
	@RequestMapping(value = "/empresaEdit", method = RequestMethod.POST)
	public String guardar(@Valid Empresa empresa, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Datos de la empresa");
			return "empresaEdit";
		}
		empresaDao.guardarEmpresa(empresa);
		status.setComplete();
		return "redirect:empresa";
	}
	
	@RequestMapping(value = "/eliminarempresa/{IdEmpresa}")
	public String eliminar(@PathVariable(value = "IdEmpresa") Long id) {
		if (id>0) {
			empresaDao.eliminarEmpresa(id);
		}
		return "redirect:/empresa";
	}
}
