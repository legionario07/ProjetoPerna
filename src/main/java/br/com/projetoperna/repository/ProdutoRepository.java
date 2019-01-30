package br.com.projetoperna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoperna.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Produto findByNome(String nome);
	Produto findByEan(String ean);
	Produto findByEanPai(String eanPai);
	List<Produto> findAllByOrderByNomeAsc();
	
}
