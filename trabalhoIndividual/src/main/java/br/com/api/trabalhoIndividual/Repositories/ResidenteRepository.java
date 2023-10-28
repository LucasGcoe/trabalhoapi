package br.com.api.trabalhoIndividual.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.trabalhoIndividual.Services.ResidenteService;



@Repository
public interface ResidenteRepository extends JpaRepository<ResidenteService, Integer>{
	@Query(value = "select * from residente wher id_residente = :residente", nativeQuery = true)
		ResidenteService findById(String residente);

	static Object findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
