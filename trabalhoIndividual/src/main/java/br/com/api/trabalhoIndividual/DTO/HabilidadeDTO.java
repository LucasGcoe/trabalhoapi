package br.com.api.trabalhoIndividual.DTO;

import java.util.Date;
import java.util.List;

public class HabilidadeDTO {
		
		
		private String descricao;
		private String nivel;

		public HabilidadeDTO(String descricao, String nivel, Date dataHabilidade) {
			super();
			this.descricao = descricao;
			this.nivel = nivel;
			
		}
		public HabilidadeDTO() {
			super();
			// TODO Auto-generated constructor stub
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
