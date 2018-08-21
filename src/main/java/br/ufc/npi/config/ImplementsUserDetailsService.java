package br.ufc.npi.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.ufc.npi.bean.Usuario;
import br.ufc.npi.repository.UsuarioRepository;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository cliRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = cliRepo.findByEmail(username);
		
		if(user == null)
			throw new UsernameNotFoundException("Usuário não encontrado");
		
		
		return new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
	}

}