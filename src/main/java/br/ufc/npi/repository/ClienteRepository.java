package br.ufc.npi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.bean.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	Cliente findByEmail(String email);
}
