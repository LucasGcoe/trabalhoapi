package br.com.api.trabalhoIndividual.Services;

import javax.management.relation.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.api.trabalhoIndividual.Repositories.RoleRepository;


@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	
	public Role save(Role role) {
		return roleRepository.save(role);
	}
}
