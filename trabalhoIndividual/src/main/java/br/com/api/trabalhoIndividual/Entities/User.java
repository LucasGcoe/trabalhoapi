package br.com.api.trabalhoIndividual.Entities;

import java.util.Set;

import javax.management.relation.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="usuarioTeste")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_residente")
    private Integer idResidente;
    @Column(name = "nome_residente")
	@NotNull 
	@Size(max=100)
    private String nomeResidente;
    @Column(name = "email_residente")
	@NotNull 
	@Size(max=100)
    private String email;
    
    @ManyToMany
    @JoinTable(
            name = "residente_role",
            joinColumns = @JoinColumn(name = "residente_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

	public User() {
		super();
	}

	public User(Integer idUser, @NotNull @Size(max = 100) String nomeUsuario, @NotNull @Size(max = 100) String email,
			Set<Role> roles, String password) {
		super();
		this.idResidente = idUser;
		this.nomeResidente = nomeUsuario;
		this.email = email;
		this.roles = roles;
		this.password = password;
	}


	public Integer getIdUser() {
		return idResidente;
	}

	public void setIdUser(Integer idUser) {
		this.idResidente = idUser;
	}

	public String getNomeUsuario() {
		return nomeResidente;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeResidente = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idResidente + ", nomeUsuario=" + nomeResidente + ", email=" + email + ", roles=" + roles + ", password=" + password + "]";
	}

	public void setNomeResidente(Object object) {
		// TODO Auto-generated method stub
		
	}

	public Object getNomeResidente() {
		// TODO Auto-generated method stub
		return null;
	}

}