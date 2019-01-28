package br.com.projetoperna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoperna.model.Combo;
import br.com.projetoperna.repository.ComboRepository;

@Service
public class ComboService {

	@Autowired
	private ComboRepository ComboRepo;

	public Combo find(Long id) {
		return ComboRepo.findById(id).get();
	}
	
	public Combo findByNome(String nome) {
		return ComboRepo.findByNome(nome);
	}

	public List<Combo> findAll() {
		return ComboRepo.findAll();
	}

	public Combo save(Combo combo) {
		return ComboRepo.save(combo);
	}

	public boolean delete(Long id) {
		
		try {
			ComboRepo.deleteById(id);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
