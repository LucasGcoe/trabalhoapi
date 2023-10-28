package br.com.api.trabalhoIndividual.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "endereco")

public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Integer id_endereco;
	@Column(name = "cep_endereco")
	@NotNull 
	@Size(max=10)
	private String cep;
	@Column(name = "logradouro_endereco")
	@NotNull 
	@Size(max=60)
	private String logradouro;
	@Column(name = "bairro_endereco")
	@NotNull 
	@Size(max=30)
	private String bairro;
	@Column(name = "localidade_endereco")
	@NotNull 
	@Size(max=35)
	private String localidade;
	@Column(name = "uf_endereco")
	@NotNull 
	@Size(max=2)
	private String uf;
	@Column(name = "ativo")
	private Boolean ativo;
	
//	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
//	@ManyToOne
//	@JoinColumn(name = "cliente_id")
//	private Cliente cliente;
	
	public Endereco() {
		super();
	}
	
	public Endereco(Integer id_endereco, @NotNull @Size(max = 10) String cep,
			@NotNull @Size(max = 60) String logradouro, @NotNull @Size(max = 30) String bairro,
			@NotNull @Size(max = 35) String localidade, @NotNull @Size(max = 2) String uf, Boolean ativo,
			Residente residente) {
		super();
		this.id_endereco = id_endereco;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.ativo = ativo;
//		this.cliente = cliente;
	}

	public Integer getId_endereco() {
		return id_endereco;
	}

	public void setId_endereco(Integer id_endereco) {
		this.id_endereco = id_endereco;
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


	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Endereco [id_endereco=" + id_endereco + ", cep=" + cep + ", logradouro=" + logradouro + ", bairro="
				+ bairro + ", localidade=" + localidade + ", uf=" + uf + ", ativo=" + ativo + "]";
	}

	

}