package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.bean.Cliente;
import br.ufc.npi.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repo;
	
	public Cliente salvarCliente(Cliente user) {
		
		repo.save(user);
		return user;
	}
	
	public List<Cliente> getUsuarios(){
		
		return repo.findAll();
	}
}
