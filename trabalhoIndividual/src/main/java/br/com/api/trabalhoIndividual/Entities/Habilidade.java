package br.com.api.trabalhoIndividual.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "habilidade")
public class Habilidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_habilidade")
	private Integer id_habilidade;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "nivel")
	private String nivel;
	public Habilidade(Integer id_habilidade, String descricao, String nivel) {
		super();
		this.id_habilidade = id_habilidade;
		this.descricao = descricao;
		this.nivel = nivel;
	}
	public Habilidade() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId_habilidade() {
		return id_habilidade;
	}
	public void setId_habilidade(Integer id_habilidade) {
		this.id_habilidade = id_habilidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	

}