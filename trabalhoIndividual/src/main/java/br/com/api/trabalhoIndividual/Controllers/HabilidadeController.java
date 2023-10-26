package br.com.api.trabalhoIndividual.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.api.trabalhoIndividual.DTO.MessageResponseDTO;
import br.com.api.trabalhoIndividual.Entities.Habilidade;
import br.com.api.trabalhoIndividual.Services.HabilidadeService;

@RestController
@RequestMapping("/habilidades")

public class HabilidadeController {
	
	@Autowired
	HabilidadeService habilidadeService;
	
	@PostMapping("/salvarHabilidade")
	public ResponseEntity<MessageResponseDTO> salvarHabilidade(@RequestBody Habilidade habilidade) {
		HabilidadeService.salvarHabilidade(habilidade);
		return ResponseEntity.ok(new MessageResponseDTO("Novo funcionário criado!"));
		}		

	@GetMapping("/listarHabilidade")
	public List<Habilidade> listarHabilidades(){
		return habilidadeService.listarHabilidades();
		
	}
	@GetMapping("/listarIdHabilidades/{id}")
	public Habilidade listarIdHabilidade(@PathVariable Integer id) {
		return habilidadeService.listarIdHabilidade(id);
	}
	@DeleteMapping("/deletarIdHabilidades/{id}")
	public void deletarIdHabilidade(@PathVariable Integer id) {
		habilidadeService.deletarIdHabilidade(id);
		ResponseEntity.ok(new MessageResponseDTO("Habilidade deletada!"));
	}
	@DeleteMapping("/desativarHabilidade/{id}")
	public void desativarHabilidade(@PathVariable Integer id) {
		habilidadeService.desativarHabilidade(id);
		ResponseEntity.ok(new MessageResponseDTO("Habilidade desativada!"));
	}
	@PutMapping("/atualizarHabilidade/{id}")
	public ResponseEntity<MessageResponseDTO> atualizarHabilidade(@PathVariable Integer id, @RequestBody Habilidade habilidade) {
		habilidadeService.atualizarHabilidade(id,habilidade);
		return ResponseEntity.ok(new MessageResponseDTO("Funcionário deletado!"));
	}
}
