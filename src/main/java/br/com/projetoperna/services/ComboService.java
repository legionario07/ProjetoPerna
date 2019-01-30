package br.com.projetoperna.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoperna.model.Combo;
import br.com.projetoperna.repository.CategoriaRepository;
import br.com.projetoperna.repository.ComboRepository;
import br.com.projetoperna.repository.UnidadeDeMedidaRepository;

@Service
public class ComboService {

	@Autowired
	private ComboRepository ComboRepo;
	
	@Autowired
	private UnidadeDeMedidaRepository uniRepo;
	
	@Autowired
	private CategoriaRepository catRepo;

	public Combo find(Long id) {
		return ComboRepo.findById(id).get();
	}
	
	public Combo findByNome(String nome) {
		return ComboRepo.findByNome(nome);
	}

	public List<Combo> findAll() {
		return ComboRepo.findAll();
	}

	@Transactional
	public Combo save(Combo combo) {
		
		combo.setCategoria(catRepo.findById(1l).get());
		combo.setUnidadeDeMedida(uniRepo.findById(1l).get());
		
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
