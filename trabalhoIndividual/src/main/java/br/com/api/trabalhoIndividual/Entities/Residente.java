package br.com.api.trabalhoIndividual.Entities;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cliente")
public class Cliente{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Integer id_cliente;	
	@Column(name = "telefone_cliente")
//	@NotNull 
	@Size(max=14)
	private String telefone;
	@Column(name = "usuario_cliente")
//	@NotNull 
	@Size(max=60)
	private String usuario;
	@Column(name = "cpf_cliente")
//	@NotNull 
	@Size(max=11)
	private String cpf;
	@Column(name = "nascimento_cliente")
//	@NotNull 
	private Date nascimento;
	@Column(name = "ativo")
	private Boolean ativo;
	
//	@OneToMany(mappedBy = "cliente")
//	private List<Endereco> enderecos;
	
	@OneToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
		
	@OneToMany
	private List<Pedido> pedidos;
			
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(Integer id_cliente, @Size(max = 14) String telefone,
			@Size(max = 60) String usuario, @Size(max = 11) String cpf,
			Date nascimento, Boolean ativo, Endereco endereco, List<Pedido> pedidos,
			User user) {
		super();
		this.id_cliente = id_cliente;
		this.telefone = telefone;
		this.usuario = usuario;
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.ativo = ativo;
		this.endereco = endereco;
		this.pedidos = pedidos;
		this.user = user;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", telefone=" + telefone + ", usuario=" + usuario + ", cpf=" + cpf
				+ ", nascimento=" + nascimento + ", ativo=" + ativo + ", pedidos=" + pedidos + ", user=" + user + "]";
	}
	
	
}
	