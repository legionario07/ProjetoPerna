package br.com.projetoperna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoperna.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

	Marca findByNome(String nome);
	
}
