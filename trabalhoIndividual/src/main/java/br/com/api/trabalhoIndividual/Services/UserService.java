package br.com.api.trabalhoIndividual.Services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import br.com.api.trabalhoIndividual.Entities.Residente;
import br.com.api.trabalhoIndividual.Repositories.ResidenteRepository;
import br.com.api.trabalhoIndividual.Repositories.UserRepository;



@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Residente findByEmail(String email){
        return ((Residente) ResidenteRepository.findByEmail(email)).get();
    }
 
    public void save(Residente residente){
        
    }
	public void listarTodos() {
		
	}
	
}