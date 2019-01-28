package br.com.projetoperna.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoperna.model.Combo;
import br.com.projetoperna.model.Pedido;
import br.com.projetoperna.model.Produto;
import br.com.projetoperna.model.Venda;
import br.com.projetoperna.repository.PedidoRepository;
import br.com.projetoperna.repository.ProdutoRepository;
import br.com.projetoperna.repository.VendaRepository;

@Service
public class VendaService {
	
	
	@Autowired
	private VendaRepository vendaRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private PedidoRepository pedidoRepo;

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
		
		
		try {
			
			for(Pedido pedido : venda.getPedidos()) {
				pedidoRepo.save(pedido);
				
				
				//É combo ou produto?
				if(pedido.getProduto() instanceof Produto) {
					Produto produto = (Produto) pedido.getProduto();
					produto.decrementarProduto(pedido.getTotal());
					
					validarSubProduto(produto);
					
					produtoRepo.save(produto);
				}else {
					
					Combo combo = (Combo) pedido.getProduto();
					for(Produto p : combo.getProdutos()) {
						p.decrementarProduto(1);
						
						validarSubProduto(p);
						
						produtoRepo.save(p);
					}
					
				}
				
			}
			
		 venda = vendaRepo.save(venda);
		}catch(Exception e) {
			return null;
		}
		
		return venda;
		
		
	}

	public boolean delete(Long id) {
		
		try {
			vendaRepo.deleteById(id);
		} catch (Exception e) {
			return false;
		}

		return true;
	}
	
	private void validarSubProduto(Produto produto) {
		
		if(produto.isSubProduto()) {
			Integer resto = produto.getQtde()/produto.getQtdeSubProduto();
			if((resto*produto.getQtdeSubProduto()<produto.getQtde())) {
				resto++;
			}
			
			Produto paiProduto = produtoRepo.findByEanPai(produto.getEanPai());
			
			if(paiProduto!=null) {
				paiProduto.setQtde(resto);
				produtoRepo.save(paiProduto);
			}
			
		}
		
	}

}
