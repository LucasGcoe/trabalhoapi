package br.com.api.trabalhoIndividual.Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.trabalhoIndividual.Configs.JWTUtil;
import br.com.api.trabalhoIndividual.DTO.LoginDTO;
import br.com.api.trabalhoIndividual.DTO.UserDTO;
import br.com.api.trabalhoIndividual.Entities.Residente;
import br.com.api.trabalhoIndividual.Entities.Endereco;
import br.com.api.trabalhoIndividual.Entities.Habilidade;
import br.com.api.trabalhoIndividual.Entities.Role;
import br.com.api.trabalhoIndividual.Entities.User;
import br.com.api.trabalhoIndividual.Enums.TipoRoleEnum;
import br.com.api.trabalhoIndividual.Repositories.ResidenteRepository;
import br.com.api.trabalhoIndividual.Repositories.EnderecoRepository;
import br.com.api.trabalhoIndividual.Repositories.RoleRepository;
import br.com.api.trabalhoIndividual.Repositories.UserRepository;
import br.com.api.trabalhoIndividual.Services.EnderecoService;
import br.com.api.trabalhoIndividual.Services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	EnderecoService enderecoService;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ResidenteRepository residenteRepository;

	@PostMapping("/registro")
	public User cadastro(@RequestParam String email, @RequestBody UserDTO user) {

		Set<String> strRoles = user.getRoles();
		Set<Role> roles = new HashSet<>();

		Endereco viaCep = enderecoService.pesquisarEndereco(user.getCep());
		Endereco enderecoNovo = new Endereco();
		enderecoNovo.setBairro(viaCep.getBairro());
		enderecoNovo.setCep(user.getCep());
		enderecoNovo.setLocalidade(viaCep.getLocalidade());
		enderecoNovo.setLogradouro(viaCep.getLogradouro());
		enderecoNovo.setUf(viaCep.getUf());
		enderecoNovo.setAtivo(true);
		enderecoRepository.save(enderecoNovo);

		User usuarioResumido = new User();
		usuarioResumido.setNomeUsuario(user.getNomeUsuario());
		usuarioResumido.setEmail(user.getEmail());

		String encodedPass = passwordEncoder.encode(user.getPassword());
		usuarioResumido.setPassword(encodedPass);

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(TipoRoleEnum.ROLE_RESIDENTE)
					.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {

				case "RESIDENTE":
					Role userRole = roleRepository.findByName(TipoRoleEnum.ROLE_RESIDENTE)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(userRole);
					Residente residente = new Residente();
					residente.setCpf(user.getCpf());
					residente.setAtivo(true);
					residente.setNascimento(user.getNascimento());
					residente.setTelefone(user.getTelefone());
					residente.setUsuario(user.getNomeUsuario());
					residente.setEndereco(enderecoNovo);
					usuarioResumido.setRoles(roles);
					userService.save(usuarioResumido);
					residente.setUser(usuarioResumido);
					residenteRepository.save(residente);
				}
			});
		}

		// emailService.envioEmailCadastro();
		return userService.save(usuarioResumido);
	}

	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody LoginDTO body) {
		try {
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					body.getEmail(), body.getPassword());

			authManager.authenticate(authInputToken);

			User user = userService.findByEmail(body.getEmail());
			User usuarioResumido = new User();
			usuarioResumido.setNomeUsuario(user.getNomeUsuario());
			usuarioResumido.setEmail(user.getEmail());
			usuarioResumido.setIdUser(user.getIdUser());
			usuarioResumido.setRoles(user.getRoles());
			String token = jwtUtil.generateTokenWithUserData(usuarioResumido);

			return Collections.singletonMap("jwt-token", token);
		} catch (AuthenticationException authExc) {
			throw new RuntimeException("Credenciais Invalidas");
		}
	}

}