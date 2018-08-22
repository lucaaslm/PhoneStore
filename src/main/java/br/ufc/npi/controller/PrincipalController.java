package br.ufc.npi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrincipalController {

	@RequestMapping(path="/")
	public String index() {
		return "redirect:/produtos/";
	}
	
	@RequestMapping(path="/logout")
	public String logout(){
		return "login";
	}
	
	@RequestMapping(path="/cadastro")
	public String cadastro() {
		return "cadastro";
	}
	
	@RequestMapping(path="/logar")
	public String logar() {
		return "login";
	}
	
	@RequestMapping(path="/produtos")
	public String produtos() {
		return "redirect:/produtos/";
	}
}
