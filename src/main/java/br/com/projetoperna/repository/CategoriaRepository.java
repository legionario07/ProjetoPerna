package br.com.projetoperna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoperna.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	Categoria findByNome(String nome);
	List<Categoria> findAllByOrderByNomeAsc();
	
}
