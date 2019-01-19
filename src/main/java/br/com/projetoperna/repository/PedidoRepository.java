package br.com.projetoperna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoperna.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	
}
