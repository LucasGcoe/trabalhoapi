package br.com.api.trabalhoIndividual.Configs;

//Este código é uma implementação de um filtro para autenticação de tokens JWT
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.api.trabalhoIndividual.Configs.UserDatailsServiceImpl.UserDetailsServiceImpl;

@Component
public class JWTFilter extends OncePerRequestFilter { //garante que o filtro seja executado apenas uma vez por solicitação

	//injeção de dependências
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private JWTUtil jwtUtil;

	// o método abaixo processa cada solicitação http
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");// pega o header que deve conter o token jwt.
		
		//abaixo, se authHeader não é nulo, não está em branco e começa com "Bearer ", o que é um prefixo comum para tokens JWT.
		if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
			
			String jwt = authHeader.substring(7); // extrai o token JWT removendo o prefixo "Bearer "
			
			//Este bloco verifica se o token JWT é nulo ou em branco e envia um erro de badRequest se for inválido.
			if (jwt == null || jwt.isBlank()) {
				
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"Foi passado um Token JWT inválido no Bearer Header");
				
			} else { //Aqui, o token JWT é validado usando o utilitário jwtUtil. Se a validação for bem-sucedida, o filtro obtém o email do usuário a partir do token.
				try {
					String email = jwtUtil.validateTokenAndRetrieveSubject(jwt);//armazena o email associado a um token jwt válido
					UserDetails userDetails = userDetailsService.loadUserByUsername(email);//detalhes do usuário são carregados usando o serviço userDetailsService
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email,
							userDetails.getPassword(), userDetails.getAuthorities());//O filtro cria um token de autenticação do Spring Security, que inclui o email do usuário, a senha (não sensível) e as autoridades do usuário.
					if (SecurityContextHolder.getContext().getAuthentication() == null) {
						//Se o contexto de segurança estiver vazio (nenhum usuário autenticado), o token de autenticação é definido no contexto de segurança.
						SecurityContextHolder.getContext().setAuthentication(authToken);
					}
				} catch (JWTVerificationException exc) { //Se houver uma exceção na verificação do token JWT, um erro de badRequest é enviado em resposta.
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Token JWT Inválido");
				} catch (Exception e) { //Se ocorrerem exceções durante o processo de autenticação, um erro de badRequest é enviado com uma mensagem de erro correspondente.
					response.sendError(HttpServletResponse.SC_BAD_REQUEST,
							"Não foi possível obter os dados do Usuario a partir do Token - " + e);
				}
			}
		}
		filterChain.doFilter(request, response); //Independentemente de o usuário ser autenticado ou não, o filtro chama filterChain.doFilter para continuar o processamento da solicitação.
	}
}