package br.com.projetoperna.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoperna.model.Pedido;
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

}
