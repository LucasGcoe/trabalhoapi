package br.com.api.trabalhoIndividual.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.trabalhoIndividual.DTO.ResidenteAtualizarDTO;
import br.com.api.trabalhoIndividual.DTO.ResidenteDTO;
import br.com.api.trabalhoIndividual.DTO.MessageResponseDTO;
import br.com.api.trabalhoIndividual.Entities.Residente;
import br.com.api.trabalhoIndividual.Services.ResidenteService;
import br.com.api.trabalhoIndividual.Services.EmailService;

@RestController
@RequestMapping("/residentes")
public class ResidenteController {

	private EmailService emailService;
	@Autowired
	ResidenteService residenteService;

	@GetMapping("/listarResidentes")
	public List<ResidenteDTO> listarResidentes() {
		return residenteService.listarResidentes();
	}

	@GetMapping("/listarResidente/{id}")
	public Residente listarIdResidente(@PathVariable Integer id) {
		return residenteService.listarIdResidente(id);
	}

	@DeleteMapping("/deletarResidente/{id}")
	public void deletarIdResidente(@PathVariable Integer id) {
		residenteService.deletarIdResidente(id);
		ResponseEntity.ok(new MessageResponseDTO("Residente deletado!"));
	}

	@DeleteMapping("/desativarResidente/{id}")
	public void desativarResidente(@PathVariable Integer id) {
		emailService.envioEmailInativo();
		residenteService.desativarResidente(id);
		ResponseEntity.ok(new MessageResponseDTO("Residente desativado!"));
	}

//	@PutMapping("/atualizarResidente/{id}")
//	public ResponseEntity<MessageResponseDTO> atualizarResidente(@PathVariable Integer id, @RequestBody Residente residente) {
//		 residenteService.atualizarResidente(id, residente);
//		 return ResponseEntity.ok(new MessageResponseDTO("Residente atualizado!"));
//	}

	@PostMapping("/atualizarResidente/{id}")
	public ResponseEntity<MessageResponseDTO> atualizarResidente(@PathVariable Integer id,
			@RequestBody ResidenteAtualizarDTO residente) {
		residenteService.atualizarResidente(id, residente);
		return ResponseEntity.ok(new MessageResponseDTO("Residente atualizado!"));
	}

	@GetMapping("/residentes/residentePorCpf/{cpf}")
	public ResidenteDTO listarResidentesPorCPF(@RequestParam("cpf") String cpf) {
		return residenteService.listarResidentesPorCPF(cpf);
	}

}
