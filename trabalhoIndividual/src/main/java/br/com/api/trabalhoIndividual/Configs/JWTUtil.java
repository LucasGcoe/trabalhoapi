package br.com.api.trabalhoIndividual.Configs;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.api.trabalhoIndividual.Entities.User;
import io.jsonwebtoken.Jwts;

//classe responsável para gerar(email e user) e validar o token
@Component
public class JWTUtil {

	//define uma propriedade que é inserida no arquivo application.properties, com seu respectivo valor
	@Value("${jwt-secret}")
	private String secret;

	@Value("${jwt-subject}")
	private String subject;

	@Value("${jwt-company-project-name}")
	private String companyProjectName;

	@Value("${jwt.expiration}")
	private Long expiration;

	//método responsável por gerar o token a partir do email
	public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {
		return JWT.create() // cria uma instância de construção do token JWT
				.withSubject(subject) //define o assunto do token
				.withClaim("email", email) // define uma reinvidicação personalizada, com o email
				.withIssuedAt(new Date()) //data que está sendo gerado o token
				.withIssuer(companyProjectName) // email da "empresa", emissor do token
				.sign(Algorithm.HMAC256(secret)); // assina o token com um algoritmo de criptografia 
	}
	
	//valida se o token JWT é válido
	public boolean validateToken(String token) {
		try {
			Jwts.parser() // criação de um parser para validação do token
			.setSigningKey(secret) // define uma chave secreta que verifica a assinatura do token
			.parseClaimsJws(token); // análise e verificação das reivindicações do token
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// gera um token a partir de um usuário
	public String generateTokenWithUserData(User user) throws IllegalArgumentException, JWTCreationException {
		//cria uma instância do ObjectMapper para serializar o objeto user em json
		ObjectMapper mapper = new ObjectMapper();
		String userJson = null; // inicializa uma string para armazenar o json do usuário
		try {
			//converte o user em uma string json
			userJson = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) { // se houver erro na conversão do json 
			e.printStackTrace();
		}

		return JWT.create()
				.withSubject(subject)
				.withClaim("usuario", userJson)
				.withIssuedAt(new Date())
				.withIssuer(companyProjectName)
				.sign(Algorithm.HMAC256(secret));
	}

	//valida o token do usuário
	public String validateTokenAndRetrieveSubject(String token) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		//cria um verificador  do token
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
				.withSubject(subject) // define o assunto do token que o verificador vai aceitar
				.withIssuer(companyProjectName)// define o emissor do token que o verificador vai aceitar
				.build(); // constroi o verificador
		//verifica o decodificador do token
		DecodedJWT jwt = verifier.verify(token);
		//cria uma instância de usuário
		User user = new User(); // 
		try {
			// desserializa o json do usuario
			user = mapper.readValue(jwt.getClaim("usuario").asString(), User.class);
		} catch (JsonProcessingException e) {
			//caso não consiga converter 
			throw new Exception("Ocorreu um erro e nao foi possivel converter o usario a partir da string json - " + e);
		}
		//retorna o email do usuário
		return user.getEmail();
	}

}