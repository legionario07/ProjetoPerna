package br.com.projetoperna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoperna.model.Combo;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Long> {

	Combo findByNome(String nome);
	
}
