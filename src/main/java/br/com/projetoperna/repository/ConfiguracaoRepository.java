package br.com.projetoperna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoperna.model.Configuracao;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {

	Configuracao findByPropriedade(String propriedade);
	List<Configuracao> findAllByOrderByPropriedadeAsc();
	
}
