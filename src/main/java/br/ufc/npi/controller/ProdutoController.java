package br.ufc.npi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.bean.Produto;
import br.ufc.npi.service.ProdutoService;


@Controller
@RequestMapping(path="/produtos")
public class ProdutoController {
	
	@Autowired
	ProdutoService service;

	@RequestMapping(path="/")
	public ModelAndView index() {
		
		ModelAndView model = new ModelAndView("produtos");
		List<Produto> produtos = service.getProdutos();
		
		model.addObject("produtos", produtos);
		
		return model;
	}
	
	@RequestMapping(path="/cadastrar")
	public String cadastrar(){
		return "cadastroProduto";
	}
	
	@RequestMapping(path="/salvar")
	public String salvarProduto(String titulo, Double valor, @RequestParam(value="imagem") MultipartFile imagem) {
		Produto produto = new Produto();
		produto.setTitulo(titulo);
		produto.setValor(valor);
		service.addProduto(produto, imagem);
		
		return "redirect:/produtos";
	}
	
	
}
