package br.com.projetoperna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetoperna.model.Categoria;
import br.com.projetoperna.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Produto findByNome(String nome);
	Produto findByEan(String ean);
	Produto findByEanPai(String eanPai);
	Produto findByEanAndIsSubProduto(String ean, Boolean isSubProduto);
	Produto findByEanPaiAndIsSubProduto(String eanPai, Boolean isSubProduto);
	List<Produto> findAllByOrderByNomeAsc();
	@Query("select u from Produto u join u.categoria where u.categoria = ?1 order by u.nome asc")
	List<Produto> findByCategoriaId(Categoria categoriaId);
	
}
