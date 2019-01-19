package br.com.projetoperna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoperna.model.Marca;
import br.com.projetoperna.repository.MarcaRepository;

@Service
public class MarcaService {

	@Autowired
	private MarcaRepository MarcaRepo;

	public Marca find(Long id) {
		return MarcaRepo.findById(id).get();
	}
	
	public Marca findByNome(String nome) {
		return MarcaRepo.findByNome(nome);
	}

	public List<Marca> findAll() {
		return MarcaRepo.findAll();
	}

	public Marca save(Marca marca) {
		return MarcaRepo.save(marca);
	}

	public boolean delete(Long id) {
		
		try {
			MarcaRepo.deleteById(id);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
