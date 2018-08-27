package br.ufc.npi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
		return "redirect:/produtos/";
	}
	
	@RequestMapping("/excluir/{id}")
	public String excluir(@PathVariable int id){
		service.removerProduto(id);
		
		return "redirect:/produtos/";
	}
	
	@RequestMapping("/editar/{id}")
	public String editar(@PathVariable int id, String titulo, double valor, String imagem) {
		service.editarProduto(id, titulo, valor, imagem);
		System.out.println("no controller: " + imagem);
		
		return "redirect:/produtos/";
	}
	
    @GetMapping("/images/{id}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable("id") Integer idProd) {

        Resource file = service.getImagem(idProd);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
