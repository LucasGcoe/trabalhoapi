package br.com.api.trabalhoIndividual.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.api.trabalhoIndividual.DTO.EnderecoDTO;
import br.com.api.trabalhoIndividual.Entities.Endereco;
import br.com.api.trabalhoIndividual.Repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	public List<Endereco> listarEnderecos(){
		return enderecoRepository.findAll();
	}
	
	public Endereco listarIdEndereco(Integer id) {
		return enderecoRepository.findById(id).get();
	}
	
	public void deletarIdEndereco(Integer id) {
		enderecoRepository.deleteById(id);
	}
	
	public void desativarEndereco(Integer id) {
		Endereco endereco = listarIdEndereco(id);
		
		if(endereco != null) {
			endereco.setAtivo(false);
			enderecoRepository.save(endereco);
		}
	}
	
	public Endereco atualizarEndereco(Integer id, Endereco endereco) {
		Endereco dadoAntigo = listarIdEndereco(id);
		
		if(endereco.getCep() != null) {
			dadoAntigo.setCep(endereco.getCep());
		}
		if(endereco.getLogradouro() != null) {
			dadoAntigo.setLogradouro(endereco.getLogradouro());
		}
		
		if(endereco.getBairro() != null) {
			dadoAntigo.setBairro(endereco.getBairro());
		}
		if(endereco.getLocalidade() != null) {
			dadoAntigo.setLocalidade(endereco.getLocalidade());
		}
		if(endereco.getUf() != null) {
			dadoAntigo.setUf(endereco.getUf());
		}
		if(endereco.getAtivo() != null) {
			dadoAntigo.setAtivo(endereco.getAtivo());
		}
		
		dadoAntigo.setId_endereco(id);
		return enderecoRepository.save(dadoAntigo);
	}

	public Endereco pesquisarEndereco(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://viacep.com.br/ws/{cep}/json/";
		Map<String, String> params = new HashMap<>();
		params.put("cep", cep);
		return restTemplate.getForObject(uri, Endereco.class, params);
	}

	public Endereco salvarEndereco(EnderecoDTO endereco) {
		Endereco viaCep = pesquisarEndereco(endereco.getCep());
		Endereco enderecoNovo = new Endereco();
		enderecoNovo.setBairro(viaCep.getBairro());
		enderecoNovo.setCep(endereco.getCep());
		enderecoNovo.setLocalidade(viaCep.getLocalidade());
		enderecoNovo.setLogradouro(viaCep.getLogradouro());
		enderecoNovo.setUf(viaCep.getUf());
		return enderecoRepository.save(enderecoNovo);
	}

}