package br.com.api.trabalhoIndividual.Controllers;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.management.relation.Role;


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
import br.com.api.trabalhoIndividual.Entities.User;
import br.com.api.trabalhoIndividual.Repositories.HabilidadeRepository;
import br.com.api.trabalhoIndividual.Repositories.ResidenteRepository;
import br.com.api.trabalhoIndividual.Repositories.RoleRepository;
import br.com.api.trabalhoIndividual.Repositories.UserRepository;
import br.com.api.trabalhoIndividual.Services.ResidenteService;
import br.com.api.trabalhoIndividual.Services.UserService;



@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

		
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	HabilidadeRepository habilidadeRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ResidenteRepository residenteRepository;
	
	
	@PostMapping("/registro")
	public void cadastro(@RequestParam String email, @RequestBody UserDTO residente) {

		Set<String> strRoles = residente.getRoles();
		Set<Role> roles = new HashSet<>();
	

		User residenteResumido = new User();
		residenteResumido.setNomeResidente(residente.getNomeResidente());
		residenteResumido.setEmail(residente.getEmail());

		String encodedPass = passwordEncoder.encode(residenteResumido.getPassword());
		residenteResumido.setPassword(encodedPass);
	}

		

	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody LoginDTO body) {
		try {
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					body.getEmail(), body.getPassword());

			authManager.authenticate(authInputToken);

			User user = ResidenteService.findByEmail(body.getEmail());
			User residenteResumido = new User();
			residenteResumido.setNomeResidente(user.getNomeResidente());
			residenteResumido.setEmail(user.getEmail());
			residenteResumido.setIdUser(user.getIdUser());
			residenteResumido.setRoles(user.getRoles());
			String token = jwtUtil.generateTokenWithUserData(residenteResumido);

			return Collections.singletonMap("jwt-token", token);
		} catch (AuthenticationException authExc) {
			throw new RuntimeException("Credenciais Invalidas");
		}
	}

}