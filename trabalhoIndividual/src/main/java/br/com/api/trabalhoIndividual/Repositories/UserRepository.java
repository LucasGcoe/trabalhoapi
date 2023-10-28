package br.com.api.trabalhoIndividual.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.trabalhoIndividual.Entities.Residente;
import br.com.api.trabalhoIndividual.Entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "select * from usuario_teste where email_usuario = :email limit 1" , nativeQuery = true)
	Optional<User> findByEmail(String email);
	
	@Query(value = "select * from cliente where cpf_cliente  = :cpf", nativeQuery = true)
	Residente findByCpf(String cpf);

}
