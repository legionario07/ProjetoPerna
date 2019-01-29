package br.com.projetoperna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoperna.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Produto findByNome(String nome);
	Produto findByEan(String ean);
	Produto findByEanPai(String eanPai);
	
}
