package br.com.projetoperna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoperna.model.Configuracao;
import br.com.projetoperna.repository.ConfiguracaoRepository;

@Service
public class ConfiguracaoService {

	@Autowired
	private ConfiguracaoRepository ConfiguracaoRepo;

	public Configuracao find(Long id) {
		return ConfiguracaoRepo.findById(id).get();
	}
	
	public Configuracao findByPropriedade(String propriedade) {
		return ConfiguracaoRepo.findByPropriedade(propriedade);
	}

	public List<Configuracao> findAll() {
		return ConfiguracaoRepo.findAll();
	}

	public Configuracao save(Configuracao Configuracao) {
		return ConfiguracaoRepo.save(Configuracao);
	}

	public boolean delete(Long id) {
		
		try {
			ConfiguracaoRepo.deleteById(id);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
