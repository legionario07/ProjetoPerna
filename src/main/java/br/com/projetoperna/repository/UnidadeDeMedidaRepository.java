package br.com.projetoperna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoperna.model.UnidadeDeMedida;

@Repository
public interface UnidadeDeMedidaRepository extends JpaRepository<UnidadeDeMedida, Long> {

	UnidadeDeMedida findByTipo(String tipo);
	List<UnidadeDeMedida> findAllByOrderByTipoAsc();
	
}
