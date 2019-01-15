package br.com.projetoperna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoperna.model.UnidadeDeMedida;
import br.com.projetoperna.repository.UnidadeDeMedidaRepository;

@Service
public class UnidadeDeMedidaService {

	@Autowired
	private UnidadeDeMedidaRepository UnidadeDeMedidaRepo;

	public UnidadeDeMedida find(Long id) {
		return UnidadeDeMedidaRepo.findById(id).get();
	}
	
	public UnidadeDeMedida findByTipo(String tipo) {
		return UnidadeDeMedidaRepo.findByTipo(tipo);
	}

	public List<UnidadeDeMedida> findAll() {
		return UnidadeDeMedidaRepo.findAll();
	}

	public UnidadeDeMedida save(UnidadeDeMedida UnidadeDeMedida) {
		return UnidadeDeMedidaRepo.save(UnidadeDeMedida);
	}

	public boolean delete(Long id) {
		
		try {
			UnidadeDeMedidaRepo.deleteById(id);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
