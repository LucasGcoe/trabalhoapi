package br.com.api.trabalhoIndividual.Enums;

public enum TipoRoleEnum {

	ROLE_RESIDENTE("RESIDENTE");
	

	private String tipo;

	TipoRoleEnum(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
}