package br.com.projetoperna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoperna.model.Categoria;
import br.com.projetoperna.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository CategoriaRepo;

	public Categoria find(Long id) {
		return CategoriaRepo.findById(id).get();
	}
	
	public Categoria findByNome(String nome) {
		return CategoriaRepo.findByNome(nome);
	}

	public List<Categoria> findAll() {
		return CategoriaRepo.findAll();
	}

	public Categoria save(Categoria Categoria) {
		return CategoriaRepo.save(Categoria);
	}

	public boolean delete(Long id) {
		
		try {
			CategoriaRepo.deleteById(id);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
