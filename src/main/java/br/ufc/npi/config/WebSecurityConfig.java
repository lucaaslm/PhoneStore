package br.ufc.npi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.csrf().disable() 
		
			.authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/img/**", "/webjars/**", "/images/**").permitAll()
				.antMatchers("/usuario/cadastro").permitAll()
				.anyRequest().authenticated()
				
				.and().formLogin().permitAll()
				
			.and()
				.formLogin()
					.loginPage("/usuario/logar")
						.permitAll()
			.and()
				.logout()
//					.logoutSuccessUrl("/login?logout")
//						.permitAll()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	public void configure(WebSecurity web) throws Exception{
		
	}
}