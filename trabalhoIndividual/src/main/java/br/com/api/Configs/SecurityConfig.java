package br.com.api.Configs;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.api.trabalhoIndividual.Repositories.ResidenteRepository;



@Configuration 
@EnableWebSecurity
public class SecurityConfig<ResidenteDetailsServicempl> extends WebSecurityConfigurerAdapter{
	
	    @Autowired
	    ResidenteRepository userRepo;

	    @Autowired
	    JWTFilter filter;

	    @Autowired
	    ResidenteDetailsServicempl uds;

	    @Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception { 
	    	http
		            .cors()
		            .and()
		            .csrf().disable()
		            .httpBasic().disable()
		            .authorizeHttpRequests()
		            .antMatchers("/residente/registro","/residente/login","/roles","/clientes/salvarResidente").permitAll()
		            .antMatchers("/residentes/salvarResidentes").hasRole("RESIDENTE")
		            .antMatchers("/habilidades/atualizarHabilidade/{id}","/habilidades/desativarHabilidade/{id}","/habilidades/deletarIdhabilidades/{id}","/habilidades/listarIdHabilidades/{id}","/habilidades/salvarHabilidade","/habilidades/listarHabilidades").hasRole("HABILIDADE")
		            .antMatchers("/residente/desativarResidente/{id}","/residente/atualizarResidente/{id}").hasAnyRole("RESIDENTE", "HABILIDADE")
		            .and()
	               .userDetailsService((UserDetailsService) uds)
	                .exceptionHandling()
	                    .authenticationEntryPoint(
	                            (request, response, authException) ->
	                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
	                    )
	                .and()
	                .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	    }
}