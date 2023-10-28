package br.com.api.trabalhoIndividual.DTO;

import java.util.Date;
import java.util.List;

public class HabilidadeDTO {
	
	private String nome_residente;
	private Integer id_residente;
	private Integer id_habilidade;
	private Date dataHabilidade;
//	private String produto_nome;
//	private String produto_descricao;
//	private Double vlr_uni;
	private List<Integer> id_habilidades;
	
	public HabilidadeDTO(String nome_residente,Integer id_residente, Integer id_habilidade,  Date dataHabilidade) {
		super();
		this.nome_residente = nome_residente;
		this.id_residente = id_residente;
		this.id_habilidade = id_habilidade;
		this.dataHabilidade = dataHabilidade;
//		this.produto_nome = produto_nome;
//		this.produto_descricao = produto_descricao;
//		this.vlr_uni = vlr_uni;
	}

	public HabilidadeDTO() {
		super();
	}

	public String getNome_residente() {
		return nome_residente;
	}

	public void setNome_residente(String nome_residente) {
		this.nome_residente = nome_residente;
	}

	public Integer getId_pedido() {
		return id_habilidade;
	}

	public void setId_pedido(Integer id_pedido, Integer id_habilidade) {
		this.id_habilidade = id_habilidade;
	}

	public Date getDataHabilidade() {
		return dataHabilidade;
	}

	public void setDataHabilidade(Date dataHabilidade) {
		this.dataHabilidade = dataHabilidade;
	}
	
	public Integer getId_residente() {
		return id_residente;
	}

	public void setId_residente(Integer id_residente) {
		this.id_residente = id_residente;
	}
	
	public List<Integer> getId_habilidades() {
		return id_habilidades;
	}

	public void setId_produtos(List<Integer> id_produtos, List<Integer> id_habilidades) {
		this.id_habilidades = id_habilidades;
	}

		
}