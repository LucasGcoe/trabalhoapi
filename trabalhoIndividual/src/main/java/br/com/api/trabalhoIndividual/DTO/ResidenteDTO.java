package br.com.api.trabalhoIndividual.DTO;

import java.util.Date;

import br.com.api.trabalhoIndividual.Entities.Endereco;

public class ResidenteDTO {
	
	private String telefone;
	private String usuario;
	private String cpf;
	private Date nascimento;
	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	
	public ResidenteDTO(String string, String string2, String string3, Date date, Endereco endereco) {
		super();
	}
	public ResidenteDTO() {
		super();
	}


	public ResidenteDTO(String telefone, String usuario, String cpf, Date nascimento, String cep, String logradouro,
			String bairro, String localidade, String uf) {
		super();
		this.telefone = telefone;
		this.usuario = usuario;
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}	

}