package br.com.api.trabalhoIndividual.DTO;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class UserDTO {

    private String nomeUsuario;
    private String email;
    private Set<String> roles;
    private String password;
    private String cpf;
    private String cep;
	private String numero;
	private String complementoAdicional;
	private String telefone;
	private Date nascimento;
	private List<String>habilidades;
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public UserDTO(String nomeUsuario, String email, Set<String> roles, String password, String cpf, String cep,
			String numero, String complementoAdicional, String telefone, Date nascimento, List<String> habilidades) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.roles = roles;
		this.password = password;
		this.cpf = cpf;
		this.cep = cep;
		this.numero = numero;
		this.complementoAdicional = complementoAdicional;
		this.telefone = telefone;
		this.nascimento = nascimento;
		this.habilidades = habilidades;
	}

	public UserDTO() {
		
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplementoAdicional() {
		return complementoAdicional;
	}
	public void setComplementoAdicional(String complementoAdicional) {
		this.complementoAdicional = complementoAdicional;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public List<String> getHabilidades() {
		return habilidades;
	}
	public void setHabilidades(List<String> habilidades) {
		this.habilidades = habilidades;
	}



}
