package br.com.projetoperna.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoperna.model.Pedido;
import br.com.projetoperna.services.PedidoService;

@RestController()
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping(path = "/findById", produces = "application/json")
	public ResponseEntity<Pedido> getPedido(@RequestParam("id") Long id) {

		ResponseEntity<Pedido> response = null;

		try {
			response = new ResponseEntity<Pedido>(pedidoService.find(id), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Pedido>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Pedido>> findAll() {

		List<Pedido> perfis = pedidoService.findAll();

		ResponseEntity<List<Pedido>> response = new ResponseEntity<>(perfis, HttpStatus.OK);

		return response;
	}

	@PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Pedido> save(@RequestBody Pedido pedido) {

		ResponseEntity<Pedido> response = null;

		try {
			pedido = pedidoService.save(pedido);
			response = new ResponseEntity<>(pedido, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		return response;

	}


	@DeleteMapping(path = "/delete", produces = "application/json")
	public ResponseEntity<Boolean> delete(@RequestParam(value = "id") Long id) {

		ResponseEntity<Boolean> response = null;
		
		try {
			response = new ResponseEntity<>(pedidoService.delete(id), HttpStatus.OK);
		}catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		

		return response;
	}

}
