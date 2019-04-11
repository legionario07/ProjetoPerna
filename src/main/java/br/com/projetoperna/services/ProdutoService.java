package br.com.projetoperna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoperna.exceptions.ProdutoSubProdutoException;
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

	public Produto save(Produto produto) {

		if (produto.getId() != null) {
			Produto produtoTemp = find(produto.getId());

			if (produto.getQtde() != produtoTemp.getQtde()) {
				Integer quantidade = produto.getQtde() - produtoTemp.getQtde();

				editarQuantidade(produto, quantidade);

			}
		}

		return ProdutoRepo.save(produto);
	}

	private Produto editarQuantidade(Produto produto, Integer quantidade) {

		if (produto.isSubProduto())
			return null;

		Produto produtoFilho = ProdutoRepo.findByEanPai(produto.getEan());

		// Se não tiver filho termina o processo
		if (produtoFilho == null)
			return produto;

		Integer qtdeFilho = produtoFilho.getQtde() + (quantidade * produtoFilho.getQtdeSubProduto());

		if (qtdeFilho < 0)
			produtoFilho.setQtde(0);
		else
			produtoFilho.setQtde(qtdeFilho);

		save(produtoFilho);

		Produto produtoNeto = ProdutoRepo.findByEanPai(produtoFilho.getEan());

		// Se não tiver neto termina o proceso
		if (produtoNeto == null)
			return produto;

		Integer qtdeNeto = produtoNeto.getQtde()
				+ ((produtoFilho.getQtdeSubProduto() * quantidade) * produtoNeto.getQtdeSubProduto());

		if (qtdeNeto < 0)
			produtoNeto.setQtde(0);
		else
			produtoNeto.setQtde(qtdeNeto);

		save(produtoNeto);

		return produto;

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
