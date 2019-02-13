package br.com.projetoperna.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	

	public List<Venda> findAllVendasAbertas() {
		List<Venda> vendasTemp = new ArrayList<Venda>();
		
		for(Venda v : vendaRepo.findAll()) {
			if(!v.getIsClosed()) {
				vendasTemp.add(v);
			}
		}
		
		return vendasTemp;
		
	}
	

	public List<Venda> findAllVendasFechadas() {
	List<Venda> vendasTemp = new ArrayList<Venda>();
		
		for(Venda v : vendaRepo.findAll()) {
			if(v.getIsClosed()) {
				vendasTemp.add(v);
			}
		}
		
		return vendasTemp;
		
	}

	public Venda save(Venda venda) {

		try {

			for (Pedido pedido : venda.getPedidos()) {
				
				if(pedido.getCombo()==null || pedido.getCombo().getId() ==null) {
					pedido.setCombo(null);
				}else if(pedido.getProduto()==null || pedido.getProduto().getId() ==null) {
					pedido.setProduto(null);
				}
				pedido = pedidoRepo.save(pedido);

				// É combo ou produto?
				if (pedido.getProduto() instanceof Produto) {
					Produto produto = (Produto) pedido.getProduto();
					produto.decrementarProduto(pedido.getTotal());

					if (produto.isSubProduto()) {
						Produto temp = validarSubProdutoMix(produto);
						if(temp!=null) {
							validarSubProduto(temp);
						}else {
							validarSeExisteMix(produto, pedido.getTotal());
							validarSubProduto(produto);
						}
					} else {
						validarProdutoPai(produto, pedido.getTotal());
					}

					produtoRepo.save(produto);
				} else {

					Combo combo = pedido.getCombo();
					for (Produto p : combo.getProdutos()) {
						p.decrementarProduto(1);

						if (p.isSubProduto()) {
							Produto temp = validarSubProdutoMix(p);
							if(temp!=null) {
								validarSubProduto(temp);
							}else {
								validarSeExisteMix(p, pedido.getTotal());
								validarSubProduto(p);
							}
						} else {
							validarProdutoPai(p, pedido.getTotal());
						}

						produtoRepo.save(p);
					}

				}

			}

			venda = vendaRepo.save(venda);
		} catch (Exception e) {
			return null;
		}

		return venda;

	}

	public Venda saveSemDecrementar(Venda venda) {

		try {
			for(Pedido pedido : venda.getPedidos()) {
				
				if(pedido.getCombo()==null || pedido.getCombo().getId() ==null) {
					pedido.setCombo(null);
				}else if(pedido.getProduto()==null|| pedido.getProduto().getId() ==null) {
					pedido.setProduto(null);
				}
				pedido = pedidoRepo.save(pedido);
				
			}

			venda = vendaRepo.save(venda);
		} catch (Exception e) {
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

		Integer resto = produto.getQtde() / produto.getQtdeSubProduto();
		if ((resto * produto.getQtdeSubProduto() < produto.getQtde())) {
			resto++;
		}

		
		Produto paiProduto = produtoRepo.findByEan(produto.getEanPai());

		if (paiProduto != null) {
			paiProduto.setQtde(resto);
			produtoRepo.save(paiProduto);
		}

	}
	
	private Produto validarSubProdutoMix(Produto produto) {

		Integer resto = produto.getQtde() / produto.getQtdeSubProduto();
		if ((resto * produto.getQtdeSubProduto() < produto.getQtde())) {
			resto++;
		}

		
		Produto paiProduto = produtoRepo.findByEanAndIsSubProduto(produto.getEanPai(), true);

		if (paiProduto != null) {
			paiProduto.setQtde(resto);
			produtoRepo.save(paiProduto);
		}

		return paiProduto;
	}
	
	private Produto validarSeExisteMix(Produto produto, Integer total) {

	
		Produto filhoProduto = produtoRepo.findByEanPaiAndIsSubProduto(produto.getEan(), true);

		if (filhoProduto != null) {
			
			int qtde = (Integer) total * filhoProduto.getQtdeSubProduto();
			
			qtde = filhoProduto.getQtde()-qtde;
			
			filhoProduto.setQtde(qtde);
			produtoRepo.save(filhoProduto);
		}

		return filhoProduto;
	}

	private void validarProdutoPai(Produto produto, Integer qtde) {

		Produto produtoSubProduto = produtoRepo.findByEanPai(produto.getEan());

		if (produtoSubProduto != null) {
			Integer resto = produtoSubProduto.getQtdeSubProduto() * qtde;
			
			if (produtoSubProduto.getQtde() - resto < 0) {
				produtoSubProduto.setQtde(0);
			} else {
				produtoSubProduto.setQtde(produtoSubProduto.getQtde() - resto);
			}
			
			produtoRepo.save(produtoSubProduto);
			
			validarSeExisteMix(produtoSubProduto, qtde*produtoSubProduto.getQtdeSubProduto());
			
		}

	}

}
