package br.com.api.trabalhoIndividual.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.trabalhoIndividual.Entities.Residente;

@Repository
public interface ResidenteRepository extends JpaRepository<Residente, Integer> {
	@Query(value = "select * from residente where cpf_residente  = :cpf", nativeQuery = true)
		Residente findByCpf(String cpf);
//		public ClienteDTO listarClientePorCPF(String cpf);
	
}

