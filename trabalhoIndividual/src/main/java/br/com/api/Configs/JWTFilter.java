package br.com.api.Configs;


import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.api.trabalhoIndividual.Entities.Residente;

@Component
public class JWTFilter<ResidentenamePasswordAuthenticationToken> extends OncePerRequestFilter { 

	
	@Autowired
	private UserDetails userDetailsService;
	@Autowired
	private JWTUtil jwtUtil;

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		
		
		if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
			
			String jwt = authHeader.substring(7); 
			
			
			if (jwt == null || jwt.isBlank()) {
				
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"Foi passado um Token JWT inválido no Bearer Header");
				
			} else { 
					String email = jwtUtil.validateTokenAndRetrieveSubject(jwt);
					Object ResidenteDetailsService;
					Residente ResidenteDetails = ((Residente) ResidenteDetailsService).loadResidenteByResidentename(email);
					ResidentenamePasswordAuthenticationToken authToken = new ResidentenamePasswordAuthenticationToken(email,
							ResidenteDetails.getPassword(), ResidenteDetails.getAuthorities());
					if (SecurityContextHolder.getContext().getAuthentication() == null) {
						
						SecurityContextHolder.getContext().setAuthentication((Authentication) authToken);
					}
				} catch (JWTVerificationException exc) { 
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Token JWT Inválido");
				} catch (Exception e) { 
					response.sendError(HttpServletResponse.SC_BAD_REQUEST,
							"Não foi possível obter os dados do Usuario a partir do Token - " + e);
				}
			
		}
		filterChain.doFilter(request, response);
	}
}