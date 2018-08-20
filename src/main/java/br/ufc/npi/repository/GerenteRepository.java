package br.ufc.npi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.bean.Gerente;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Integer>{

}
