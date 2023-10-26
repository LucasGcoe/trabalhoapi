package br.com.api.trabalhoIndividual.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "habilidade")

public class Habilidade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_habilidade")
	private Integer id_habilidade;
	
	@Column(name = "habilidade")
//	@NotNull 
	@Size(max=400)
	private String habilidade;

	@ManyToOne
	@JoinColumn(name = "id_residente", referencedColumnName = "id_residente")
	private Residente residente;

	public Habilidade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Habilidade(Integer id_habilidade, @Size(max = 400) String habilidade, Residente residente) {
		super();
		this.id_habilidade = id_habilidade;
		this.habilidade = habilidade;
		this.residente = residente;
	}

	public Integer getId_habilidade() {
		return id_habilidade;
	}

	public void setId_habilidade(Integer id_habilidade) {
		this.id_habilidade = id_habilidade;
	}

	public String getHabilidade() {
		return habilidade;
	}

	public void setHabilidade(String habilidade) {
		this.habilidade = habilidade;
	}

	public Residente getResidente() {
		return residente;
	}

	public void setResidente(Residente residente) {
		this.residente = residente;
	}

	@Override
	public String toString() {
		return "Habilidade [id_habilidade=" + id_habilidade + ", habilidade=" + habilidade + ", residente=" + residente
				+ "]";
	}



}

