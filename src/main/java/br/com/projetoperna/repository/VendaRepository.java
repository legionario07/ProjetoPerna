package br.com.projetoperna.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoperna.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

	Venda findByDataVenda(Calendar dataVenda);
	List<Venda> findByIsClosed(Boolean isClosed);
	
}
