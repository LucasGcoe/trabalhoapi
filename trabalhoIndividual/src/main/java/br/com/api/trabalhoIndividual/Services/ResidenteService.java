package br.com.api.trabalhoIndividual.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.trabalhoIndividual.DTO.ResidenteAtualizarDTO;
import br.com.api.trabalhoIndividual.DTO.ResidenteDTO;
import br.com.api.trabalhoIndividual.Entities.Endereco;
import br.com.api.trabalhoIndividual.Entities.Residente;
import br.com.api.trabalhoIndividual.Repositories.EnderecoRepository;
import br.com.api.trabalhoIndividual.Repositories.ResidenteRepository;

@Service
public class ResidenteService {

	@Autowired
	ResidenteRepository residenteRepository;
	@Autowired
	EnderecoService enderecoService;
	@Autowired
	EnderecoRepository enderecoRepository;
//	public void  salvarResidente(Residente residente) {
//		System.out.println(residente.toString());
//
//		Endereco viaCep = enderecoService.pesquisarEndereco(residente.getEndereco().getCep());
//		Endereco enderecoNovo = new Endereco();
//		enderecoNovo.setBairro(viaCep.getBairro());
//		enderecoNovo.setCep(residente.getEndereco().getCep());
//		enderecoNovo.setLocalidade(viaCep.getLocalidade());
//		enderecoNovo.setLogradouro(viaCep.getLogradouro());
//		enderecoNovo.setUf(viaCep.getUf());
//		System.out.println(enderecoNovo.toString());
//		residente.setEndereco(enderecoNovo);
//		enderecoRepository.save(enderecoNovo);
//		residenteRepository.save(residente);
//
//	}

	public List<ResidenteDTO> listarResidentes() {

		List<ResidenteDTO> infoResidentes = new ArrayList<>();
		List<Residente> residentes = residenteRepository.findAll();
		for (Residente residente : residentes) {
			infoResidentes.add(converterResidenteDTO(residente));
		}
		return infoResidentes;

	}

	public ResidenteDTO converterResidenteDTO(Residente residente) {
		ResidenteDTO residenteConvertido = new ResidenteDTO();
		residenteConvertido.setCpf(residente.getCpf());
		residenteConvertido.setBairro(residente.getEndereco().getBairro());
		residenteConvertido.setCep(residente.getEndereco().getCep());
		residenteConvertido.setCpf(residente.getCpf());
		residenteConvertido.setLocalidade(residente.getEndereco().getLocalidade());
		residenteConvertido.setLogradouro(residente.getEndereco().getLogradouro());
		residenteConvertido.setNascimento(residente.getNascimento());
		residenteConvertido.setTelefone(residente.getTelefone());
		residenteConvertido.setUf(residente.getEndereco().getUf());
		residenteConvertido.setUsuario(residente.getUsuario());
		return residenteConvertido;
	}

	public Residente listarIdResidente(Integer id) {
		return residenteRepository.findById(id).get();
	}

	public void deletarIdResidente(Integer id) {
		residenteRepository.deleteById(id);
	}

	public void desativarResidente(Integer id) {
		Residente residente = listarIdResidente(id);

		if (residente != null) {
			residente.setAtivo(false);
			residenteRepository.save(residente);
		}
	}

	public Residente atualizarResidente(Integer id, ResidenteAtualizarDTO residente) {

		Residente dadoAntigo = listarIdResidente(id);

		if (residente.getCpf() != null && residente.getCpf() != dadoAntigo.getCpf()) {
			dadoAntigo.setCpf(residente.getCpf());
		}

		if (residente.getNascimento() != null && residente.getNascimento() != dadoAntigo.getNascimento()) {
			dadoAntigo.setNascimento(residente.getNascimento());
		}

		if (residente.getTelefone() != null && residente.getTelefone() != dadoAntigo.getTelefone()) {
			dadoAntigo.setTelefone(residente.getTelefone());
		}

		if (residente.getUsuario() != null && residente.getUsuario() != dadoAntigo.getUsuario()) {
			dadoAntigo.setUsuario(residente.getUsuario());
		}

		if (residente.getCep() != null && residente.getCep() != dadoAntigo.getEndereco().getCep()) {
			dadoAntigo.getEndereco().setCep(residente.getCep());
		}

		dadoAntigo.setId_residente(id);

		return residenteRepository.save(dadoAntigo);
	}

	public ResidenteDTO listarResidentesPorCPF(String cpf) {

		Residente residente = residenteRepository.findByCpf(cpf);
		return converterResidenteDTO(residente);
	}

	public Residente findByCpf(String cpf) {
		return residenteRepository.findByCpf(cpf);
	}

	public Residente findById_residente(Integer id) {
		return residenteRepository.findById(id).get();
	}

}