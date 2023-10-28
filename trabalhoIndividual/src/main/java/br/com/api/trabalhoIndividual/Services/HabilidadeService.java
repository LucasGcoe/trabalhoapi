package br.com.api.trabalhoIndividual.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.trabalhoIndividual.DTO.MessageResponseDTO;
import br.com.api.trabalhoIndividual.DTO.HabilidadeDTO;
import br.com.api.trabalhoIndividual.Entities.Residente;
import br.com.api.trabalhoIndividual.Entities.Habilidade;

import br.com.api.trabalhoIndividual.Repositories.ResidenteRepository;
import br.com.api.trabalhoIndividual.Repositories.HabilidadeRepository;

@Service
public class HabilidadeService {

	@Autowired
	HabilidadeRepository habilidadeRepository;

	@Autowired
	ResidenteRepository residenteRepository;

	@Autowired
	ResidenteService residenteService;

	@Autowired
	UserService userService;

	public Habilidade converterHabilidadeDTO(HabilidadeDTO habilidade) {
		Habilidade habilidadeConvertido = new Habilidade();
		habilidadeConvertido.setDescricao(habilidade.getDescricao());
		habilidadeConvertido.setNivel(habilidade.getNivel());

		return habilidadeConvertido;
	}

	public ResponseEntity<MessageResponseDTO> salvarHabilidade(HabilidadeDTO habilidadeDTO, String cpf) {
		Habilidade p = converterHabilidadeDTO(habilidadeDTO);
		List<Habilidade> habilidades = new ArrayList<>();

		Residente residente = residenteService.findByCpf(cpf);
		habilidades.add(p);
		residente.setHabilidades(habilidades);
		habilidadeRepository.save(p);
		residenteRepository.save(residente);
		return ResponseEntity.ok(new MessageResponseDTO("Nova habilidade criada com sucesso!"));
	}

	public List<Habilidade> listarHabilidades() {
		return habilidadeRepository.findAll();
	}

	public Habilidade listarIdHabilidade(Integer id) {
		return habilidadeRepository.findById(id).get();
	}

	public void deletarIdHabilidade(Integer id) {
		habilidadeRepository.deleteById(id);
	}

	public void desativarHabilidade(Integer id) {
		Habilidade habilidade = listarIdHabilidade(id);
		if (habilidade != null) {

			habilidadeRepository.save(habilidade);
		}
	}

	public Habilidade atualizarHabilidade(Integer id, Habilidade habilidade) {
		Habilidade dadoAntigo = listarIdHabilidade(id);

		dadoAntigo.setId_habilidade(id);
		return habilidadeRepository.save(dadoAntigo);
	}
}
