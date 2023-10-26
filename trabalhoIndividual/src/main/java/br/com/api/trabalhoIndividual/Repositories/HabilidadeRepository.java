package br.com.api.trabalhoIndividual.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.trabalhoIndividual.Entities.Habilidade;

@Repository
public interface HabilidadeRepository extends JpaRepository<Habilidade,Integer> {

}
