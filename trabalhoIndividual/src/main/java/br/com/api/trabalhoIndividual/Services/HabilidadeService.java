package br.com.api.trabalhoIndividual.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.trabalhoIndividual.DTO.HabilidadeDTO;
import br.com.api.trabalhoIndividual.DTO.MessageResponseDTO;
import br.com.api.trabalhoIndividual.Entities.Habilidade;
import br.com.api.trabalhoIndividual.Repositories.HabilidadeRepository;
import br.com.api.trabalhoIndividual.Repositories.ResidenteRepository;

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
	


	public HabilidadeDTO converterPedidoDTO(Habilidade habilidade) {
		HabilidadeDTO pedidoConvertido = new HabilidadeDTO();
		habilidadeConvertido.setId_habilidade(habilidade.getId_habilidade());
		habilidadeConvertido.setNome_residente(habilidade.getResidente().getUsuario());
		habilidadeConvertido.setId_residente(habilidade.getResidente().getId_residente());
		habilidadeConvertido.setDataHabilidade(habilidade.getDataHabilidade());
		
		List<Habilidade> habilidades = new ArrayList<>();
		for(Integer id_habilidade : habilidadeConvertido.getId_habilidades()) {
			Habilidade habilidade = habilidadeRepository.findById(id_habilidade).get();
			habilidades.add(habilidade);
		}
		habilidade.setHabilidade(habilidades);
			
		return pedidoConvertido;
	}

		
	public ResponseEntity<MessageResponseDTO> salvarPedido(HabilidadeDTO habilidadeDTO) {
		Habilidade p = new Habilidade(); 
				
		p.setId_habilidade(habilidadeDTO.getId_habilidade());
		p.setDataHabilidade(habilidadeDTO.getDataHabilidade());
		
		List<Habilidade> habilidades = new ArrayList<>();
		for(Integer idHabilidades : habilidadeDTO.getId_habilidades()) {
			Habilidade habilidade = habilidadeRepository.findById(idHabilidade).get();
			habilidades.add(habilidade);
			
		}
		p.setHabilidade(habilidades);
		Residente residente = residenteService.findById_residente(habilidadeDTO.getId_residente());
		p.setResidente(residente);
		habilidadeRepository.save(p);
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
        if (habilidade!= null) {
        	habilidade.setAtivo(false);
        	habilidadeRepository.save(habilidade);
        }        
	}
	
	public Habilidade atualizarPedido(Integer id, Habilidade habilidade) {
		Habilidade dadoAntigo = listarIdHabilidade(id);
		
		if(habilidade.getAtivo()!=null) { 
			dadoAntigo.setAtivo(habilidade.getAtivo());	
		}
		
		if(habilidade.getDataHabilidade()!=null) {
			dadoAntigo.setDataHabilidade(habilidade.getDataHabilidade());
		}
		
		dadoAntigo.setId_habilidade(id);		
			return habilidadeRepository.save(dadoAntigo);
	}
}
