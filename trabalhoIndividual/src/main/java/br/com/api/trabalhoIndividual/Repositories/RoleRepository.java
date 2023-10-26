package br.com.api.trabalhoIndividual.Repositories;

import java.util.Optional;
import java.util.Set;

import javax.management.relation.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.trabalhoIndividual.Enums.TipoRoleEnum;

@Repository

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(TipoRoleEnum roleUser);

	@Query(value = "select r.* from residenteTeste u \rzn"
			+ "inner join residenteRole ur on u.id_residente = ur.residente_id\r\n"
			+ "inner join roles r on ur.role_id = r.id\r\n"
			+ "where u.email_residente = :email", nativeQuery=true)
	Set<Role> roles (String email);
}
