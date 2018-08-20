package br.ufc.npi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.bean.Cliente;
import br.ufc.npi.service.ClienteService;

@Controller
@RequestMapping(path="/cliente")
public class ClienteController {
	@Autowired
	ClienteService service;
	
	@RequestMapping("/logar")
	public ModelAndView logar() {
		
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	@RequestMapping(path="/salvar", method=RequestMethod.POST)
	public String salvarUsuario(@RequestParam String nome, @RequestParam String email, @RequestParam String senha) {
		
		Cliente user = new Cliente();
		user.setNome(nome);
		user.setEmail(email);
		user.setSenha(senha);
		
		service.salvarCliente(user);
		
		return "redirect:/cadastro";
	}
}
