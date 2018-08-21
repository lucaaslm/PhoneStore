package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.npi.bean.Produto;
import br.ufc.npi.repository.ProdutoRepository;
import br.ufc.npi.util.UsingFileUtils;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repo;
	
	public List<Produto> getProdutos(){
		
		return repo.findAll();
	}
	
	public Produto getProduto(int id) {
		return repo.findById(id);
	}

	public void addProduto(Produto produto, MultipartFile imagem) {
		String caminho = "/images/" + produto.getTitulo().replaceAll(" ", "") + ".jpg";
		UsingFileUtils.salvarImagem(caminho, imagem);
		
		produto.setCaminhoImagem(caminho);
		repo.save(produto);
	}
	
	public void removerProduto(int id) {
		repo.deleteById(id);
	}
}
