package br.com.projetoperna.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoperna.model.Produto;
import br.com.projetoperna.model.Venda;
import br.com.projetoperna.repository.ProdutoRepository;
import br.com.projetoperna.repository.VendaRepository;

@Service
public class VendaService {
	
	
	@Autowired
	private VendaRepository vendaRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;

	public Venda find(Long id) {
		return vendaRepo.findById(id).get();
	}
	
	public Venda findByDataVenda(String dataVenda) {
		
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(new SimpleDateFormat("ddMMyyyy").parse(dataVenda));
		} catch (ParseException e) {
			e.printStackTrace();
			return new Venda();
		}
		
		return vendaRepo.findByDataVenda(c);
	}

	public List<Venda> findAll() {
		return vendaRepo.findAll();
	}

	public Venda save(Venda venda) {
		
		/**
		 * Existem produtos que podem ser divididos em X unidades, 
		 * se essas X unidades acabarem deverá ser decrementado uma unidade do PRODUTO
		 * Deverá ser verificado se é ultima unidade de X para decrementar
		 */
		
		Map<Produto, Integer> mapProdutos = new HashMap<Produto, Integer>();
		
		//É um subProduto, se for adiciona no map com sua frequencia
		for(Produto p : venda.getProdutos()) {
			if(p.isSubProduto()) {
				if(mapProdutos.containsKey(p)) {
					Integer valor = mapProdutos.get(p);
					mapProdutos.put(p, valor++);
				}else {
					mapProdutos.put(p, 1);
				}
			}
		}
		
		Set<Produto> keys = mapProdutos.keySet();
		
		for(Produto produtoKey : keys) {
			Integer total = mapProdutos.get(produtoKey);
			if(total>=produtoKey.getQteDivisao()) {
				Integer valorADecrementar = total/produtoKey.getQteDivisao();
				produtoKey.decrementarProduto(valorADecrementar);
				
				produtoRepo.save(produtoKey);
				
			}
			
		}
		
		return vendaRepo.save(venda);
	}

	public boolean delete(Long id) {
		
		try {
			vendaRepo.deleteById(id);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
