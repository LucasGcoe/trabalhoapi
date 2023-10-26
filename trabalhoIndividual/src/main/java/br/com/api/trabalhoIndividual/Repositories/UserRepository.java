package br.com.api.trabalhoIndividual.Repositories;

import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.trabalhoIndividual.DTO.UserDTO;
import br.com.api.trabalhoIndividual.Entities.Residente;

public interface UserRepository extends JpaRepository<UserDTO, Integer> {

	@Query(value = "select * from residente_teste where email_usuario = :email limit 1" , nativeQuery = true)
	Optional<UserDTO> findByEmail(String email);
	
	@Query(value = "select * from residente where cpf_residente  = :cpf", nativeQuery = true)
	Residente findByCpf(String cpf);

	User save(User user);

}
