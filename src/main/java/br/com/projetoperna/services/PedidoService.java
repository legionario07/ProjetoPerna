package br.com.projetoperna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoperna.model.Pedido;
import br.com.projetoperna.repository.PedidoRepository;

@Service
public class PedidoService {
	
	
	@Autowired
	private PedidoRepository pedidoRepo;

	public Pedido find(Long id) {
		return pedidoRepo.findById(id).get();
	}
	

	public List<Pedido> findAll() {
		return pedidoRepo.findAll();
	}

	public Pedido save(Pedido pedido) {
		
		try {
		 pedido = pedidoRepo.save(pedido);
		}catch(Exception e) {
			return null;
		}
		
		return pedido;
		
	}

	public boolean delete(Long id) {
		
		try {
			pedidoRepo.deleteById(id);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
