package br.com.api.trabalhoIndividual.DTO;

import java.util.Date;

public class ResidenteAtualizarDTO {

	private String telefone;

	private String usuario;

	private String cpf;

	private Date nascimento;

	private String cep;

	public ResidenteAtualizarDTO() {
		super();
	}

	public ResidenteAtualizarDTO(String telefone, String usuario, String cpf, Date nascimento, String cep) {
		super();
		this.telefone = telefone;
		this.usuario = usuario;
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}