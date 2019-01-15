package br.com.projetoperna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoperna.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	Perfil findByNome(String nome);
	
}
