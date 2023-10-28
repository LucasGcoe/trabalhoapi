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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.trabalhoIndividual.DTO.HabilidadeDTO;
import br.com.api.trabalhoIndividual.DTO.MessageResponseDTO;
import br.com.api.trabalhoIndividual.Entities.Habilidade;
import br.com.api.trabalhoIndividual.Services.EmailService;
import br.com.api.trabalhoIndividual.Services.HabilidadeService;

@RestController
@RequestMapping("/habilidades")
public class HabilidadeController {
	
	private EmailService emailService; //EM TODOS OS CONTROLES QUE TIVER DISPARO DE EMAIL DEVE TER !!!!
	 @Autowired
	    public void setEmailService(EmailService emailService) {
	        this.emailService = emailService;
	    }
	 
	@Autowired
	HabilidadeService habilidadeService;
	
	@PostMapping("/salvarHabilidade") 
	public ResponseEntity<MessageResponseDTO> salvarHabilidade(@RequestBody HabilidadeDTO habilidadeDTO) {
		emailService.envioEmailHabilidadeRealizado();
		habilidadeService.salvarHabilidade(habilidadeDTO);	
		return ResponseEntity.ok(new MessageResponseDTO("Nova habilidade criado com sucesso!"));
	}
	
	@GetMapping("/listarHabilidades")
	public List<Habilidade> listarHabilidades() {
		return habilidadeService.listarHabilidades();
	}
	
	@GetMapping("/listarIdHabilidade/{id}")
	public Habilidade listarIdHabilidade(@PathVariable Integer id) {
		return habilidadeService.listarIdHabilidade(id);
	}
	
	@DeleteMapping("/deletarIdHabilidade/{id}")
	public void deletarIdHabilidadeo(@PathVariable Integer id) {
		emailService.envioEmailHabilidadeCancel();
		habilidadeService.deletarIdHabilidade(id);
		ResponseEntity.ok(new MessageResponseDTO("Habilidade deletada com sucesso!"));
	}
	
	@DeleteMapping("/desativarHabilidade/{id}")
	public void desativarHabilidade(@PathVariable Integer id) {
		habilidadeService.desativarHabilidade(id);
		ResponseEntity.ok(new MessageResponseDTO("Habilidade desativada com sucesso!"));
	}
	
	@PutMapping("/atualizarHabilidade/{id}")
	public ResponseEntity<MessageResponseDTO> atualizaHabilidade(@PathVariable Integer id, @RequestBody Habilidade habilidade) {
		habilidadeService.atualizarHabilidade(id, habilidade);
		return ResponseEntity.ok(new MessageResponseDTO("Novo pedido criado com sucesso!"));
	}
	
	@GetMapping("/habilidades/habilidades")
	public HabilidadeDTO listarHabilidadePorResidente(@RequestParam("cpf") String cpf) {
		return listarHabilidadePorResidente(cpf);
		
	}
	
}