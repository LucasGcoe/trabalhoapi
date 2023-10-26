package br.com.api.trabalhoIndividual.Entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "residente")

public class Residente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_residente")
	private Integer id_residente;

	@Column(name = "residente")
//	@NotNull 
	@Size(max = 400)
	private String residente;

	@OneToMany
	private List<Habilidade> habilidades;

	public Residente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Residente(Integer id_residente, @Size(max = 400) String residente, List<Habilidade> habilidades) {
		super();
		this.id_residente = id_residente;
		this.residente = residente;
		this.habilidades = habilidades;
	}

	public Integer getId_residente() {
		return id_residente;
	}

	public void setId_residente(Integer id_residente) {
		this.id_residente = id_residente;
	}

	public String getResidente() {
		return residente;
	}

	public void setResidente(String residente) {
		this.residente = residente;
	}

	public List<Habilidade> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<Habilidade> habilidades) {
		this.habilidades = habilidades;
	}

	@Override
	public String toString() {
		return "Residente [id_residente=" + id_residente + ", residente=" + residente + ", habilidades=" + habilidades
				+ "]";
	}

	public Residente get() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public static CharSequence getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public Residente loadResidenteByResidentename(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}