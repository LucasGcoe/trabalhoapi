package br.com.api.trabalhoIndividual.DTO;

import java.util.Set;

public class UserDTO {
	

	    private String nomeResidente;
	    private String email;
	    private Set<String> roles;
	    private String password;
	    private String cpf;
	    


		public String getNomeResidente() {
			return nomeResidente;
		}

		public void setNomeResidente(String nomeResidente) {
			this.nomeResidente = nomeResidente;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public void setRoles(Set<String> roles) {
			this.roles = roles;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public UserDTO() {
			super();
		}

	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<String> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}
	public UserDTO(String cpf, String nomeResidente, String email, Set<String> roles, String password) {
		super();
		this.cpf = cpf;
		this.nomeResidente = nomeResidente;
		this.email = email;
		this.roles = roles;
		this.password = password;
	
		
	
		
		
	}

}
