package br.ufc.npi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.bean.Usuario;
import br.ufc.npi.service.UsuarioService;

@Controller
@RequestMapping(path="/usuario")
public class UsuarioController {
	@Autowired
	UsuarioService service;
	
	@RequestMapping("/logar")
	public ModelAndView logar() {
		
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	@RequestMapping("/cadastro")
	public String cadastro() {
		
		return "cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvarUsuario(@RequestParam String nome, @RequestParam String email, @RequestParam String senha) {
		
		Usuario user = new Usuario();
		user.setNome(nome);
		user.setLogin(email);
		user.setSenha(senha);
		
		service.salvarCliente(user);
		
		return "redirect:/cadastro";
	}
}
