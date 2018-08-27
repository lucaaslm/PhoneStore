package br.ufc.npi.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.npi.bean.Produto;
import br.ufc.npi.repository.ProdutoRepository;
import br.ufc.npi.util.UsingFileUtils;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repo;
	
	@Value("${imagens.diretorio}")
	private String diretorioArq;
	
	public List<Produto> getProdutos(){
		
		return repo.findAll();
	}
	
	public Produto getProduto(int id) {
		return repo.getOne(id);
	}

	public void addProduto(Produto produto, MultipartFile imagem) {
		String caminho = diretorioArq + produto.getTitulo().replaceAll(" ", "") + ".jpg";
		UsingFileUtils.salvarImagem(caminho, imagem);
		
		produto.setCaminhoImagem(caminho);
		repo.save(produto);
	}
	
	public void removerProduto(int id) {
		repo.deleteById(id);
	}
	
	public void editarProduto(int id, String titulo, double valor, String caminhoImagem) {
		Produto prod = new Produto();
		prod.setId(id);
		prod.setTitulo(titulo);
		prod.setValor(valor);
		prod.setCaminhoImagem(caminhoImagem);
		System.out.println("no service: " + caminhoImagem);
	
		repo.save(prod);
	}
	
	public Resource getImagem(Integer idProd) {
		Produto prod = repo.getOne(idProd);
		
		File arq = new File(prod.getCaminhoImagem());
		
		Resource resource = new FileSystemResource(arq);
		return resource;
	}
}
