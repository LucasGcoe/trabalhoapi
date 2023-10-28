package br.com.api.trabalhoIndividual.Entities;



import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.api.trabalhoIndividual.Services.ResidenteService;

@Entity
@Table(name = "habilidade")
public class Habilidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_habilidade")
	private Integer id_habilidade; 
	@Column(name = "data_habilidade")
	@NotNull 
	private Date datahabilidade;
	@Column(name = "ativo")
	private Boolean ativo = true;
	private String nome;
	public Integer getId_habilidade() {
		return id_habilidade;
	}
	public void setId_habilidade(Integer id_habilidade) {
		this.id_habilidade = id_habilidade;
	}
	public Date getDatahabilidade() {
		return datahabilidade;
	}
	public void setDatahabilidade(Date datahabilidade) {
		this.datahabilidade = datahabilidade;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Habilidade(Integer id_habilidade, @NotNull Date datahabilidade, Boolean ativo, String nome) {
		super();
		this.id_habilidade = id_habilidade;
		this.datahabilidade = datahabilidade;
		this.ativo = ativo;
		this.nome = nome;
	}
	public Habilidade() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		
}
