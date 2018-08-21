package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.ufc.npi.bean.Usuario;
import br.ufc.npi.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository repo;
	
	public Usuario salvarCliente(Usuario user) {
		
		user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
		repo.save(user);
		return user;
	}
	
	public List<Usuario> getUsuarios(){
		
		return repo.findAll();
	}
}
