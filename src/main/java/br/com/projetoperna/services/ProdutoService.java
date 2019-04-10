package br.com.projetoperna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoperna.model.Categoria;
import br.com.projetoperna.model.Produto;
import br.com.projetoperna.repository.CategoriaRepository;
import br.com.projetoperna.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository ProdutoRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto find(Long id) {
		return ProdutoRepo.findById(id).get();
	}
	
	public Produto findByNome(String nome) {
		return ProdutoRepo.findByNome(nome);
	}

	public List<Produto> findAll() {
		return ProdutoRepo.findAllByOrderByNomeAsc();
	}
	
	public List<Produto> findByCategoriaId(Long categoriaId) {
		Categoria categoria = categoriaRepository.findById(categoriaId).get();
		return ProdutoRepo.findByCategoriaId(categoria);
	}


	public Produto findByEanAndIsSubProduto(String eanPai, Boolean isSubProduto) {
		
		return ProdutoRepo.findByEanAndIsSubProduto(eanPai, isSubProduto);
	}
	
	public Produto findByEanPaiAndIsSubProduto(String eanPai, Boolean isSubProduto) {
		
		return ProdutoRepo.findByEanPaiAndIsSubProduto(eanPai, isSubProduto);
	}
	
	public Produto findByEanPai(String ean) {
		return ProdutoRepo.findByEanPai(ean);
	}
	
	public Produto save(Produto Produto) {
		return ProdutoRepo.save(Produto);
	}

	public boolean delete(Long id) {
		
		try {
			ProdutoRepo.deleteById(id);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
