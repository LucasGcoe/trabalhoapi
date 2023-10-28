package br.com.api.trabalhoIndividual.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.trabalhoIndividual.DTO.EnderecoDTO;
import br.com.api.trabalhoIndividual.Entities.Endereco;
import br.com.api.trabalhoIndividual.Services.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	EnderecoService enderecoService;
	
	@PostMapping("/salvarEndereco")
	public Endereco salvarEndereco(@RequestBody Endereco endereco) {
		return enderecoService.salvarEndereco(endereco);
	}
	
	@GetMapping("/listarEnderecos")
	public List<Endereco> listarEnderecos(){
		return enderecoService.listarEnderecos();
	}
	
	@GetMapping("/listarEndereco/{id}")
	public Endereco listarIdEndereco(@PathVariable Integer id) {
		return enderecoService.listarIdEndereco(id);
	}
	
	@DeleteMapping("/deletarEndereco/{id}")
	public void deletarIdEndereco(@PathVariable Integer id) {
		enderecoService.deletarIdEndereco(id);
	}
	
	@DeleteMapping("/desativarEndereco/{id}")
	public void desativarEndereco(@PathVariable Integer id) {
		enderecoService.desativarEndereco(id);
	}
			
	@PutMapping("/atualizarEndereco/{id}")
	public Endereco atualizarEndereco(@PathVariable Integer id, @RequestBody Endereco endereco) {
		return enderecoService.atualizarEndereco(id, endereco);
	}
	
}