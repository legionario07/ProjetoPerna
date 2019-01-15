package br.com.projetoperna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoperna.model.Perfil;
import br.com.projetoperna.repository.PerfilRepository;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepo;

	public Perfil find(Long id) {
		return perfilRepo.findById(id).get();
	}
	
	public Perfil findByNome(String nome) {
		return perfilRepo.findByNome(nome);
	}

	public List<Perfil> findAll() {
		return perfilRepo.findAll();
	}

	public Perfil save(Perfil perfil) {
		return perfilRepo.save(perfil);
	}

	public boolean delete(Long id) {
		
		try {
			perfilRepo.deleteById(id);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
