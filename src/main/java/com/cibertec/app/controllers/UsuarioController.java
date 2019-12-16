package com.cibertec.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cibertec.app.models.dao.IUsuarioService;
import com.cibertec.app.models.entity.Usuario;


@Controller
@SessionAttributes("usuario")
public class UsuarioController {

	@Autowired(required = true)
	private IUsuarioService usuarioService;

//	@RequestMapping(value = "/index")
//	public void index() {
//	}
//	
//	@RequestMapping(value = "/main")
//	public void main() {
//	}
//	
//	@RequestMapping(value = "dashboard/index")
//	public void dashboard() {
//	}

	@RequestMapping(value = "/login")
	public String login(Map<String, Object> model) {
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("titulo", "Formulario de Usuario");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Validated Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			return "login";
		}
		try {
			usuario = usuarioService.loginUsuario(usuario.getCorreo(), usuario.getPassword());
			
			if(usuario.equals(null)) {
				System.out.println("Datos incorrectos");
				return "redirect:/login";
			} else {
				System.out.println("Bienvenido: " + usuario);
				return "redirect:/listar";
			}
		} catch (Exception e) {
			return "redirect:/login";
		}
		
	}

	@RequestMapping(value = "/registro")
	public String crear(Map<String, Object> model) {

		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("titulo", "Formulario de Cliente");
		return "registro";
	}


	@RequestMapping(value = "/registro", method = RequestMethod.POST)
	public String guardar(@Validated Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}
		if(usuario.getCorreo() == "" || usuario.getNombre() == "" || usuario.getPassword() == "") {
			return "redirect:/registro";
		}else {
			usuarioService.save(usuario);
			status.setComplete();
			return "redirect:/listar";
		}		
	}
}
