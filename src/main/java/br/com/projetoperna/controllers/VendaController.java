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

import br.com.projetoperna.model.Venda;
import br.com.projetoperna.services.VendaService;

@RestController()
@RequestMapping("/vendas")
public class VendaController {

	@Autowired
	private VendaService vendaService;

	@GetMapping(path = "/findById", produces = "application/json")
	public ResponseEntity<Venda> getVenda(@RequestParam("id") Long id) {

		ResponseEntity<Venda> response = null;

		try {
			response = new ResponseEntity<Venda>(vendaService.find(id), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Venda>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Venda>> findAll() {

		List<Venda> vendas = vendaService.findAll();

		ResponseEntity<List<Venda>> response = new ResponseEntity<>(vendas, HttpStatus.OK);

		return response;
	}
	
	@GetMapping(path = "/findAllVendasAbertas", produces = "application/json")
	public ResponseEntity<List<Venda>> findAllVendasAbertas() {

		List<Venda> vendas = vendaService.findAllVendasAbertas();

		ResponseEntity<List<Venda>> response = new ResponseEntity<>(vendas, HttpStatus.OK);

		return response;
	}
	
	@GetMapping(path = "/findAllVendasFechadas", produces = "application/json")
	public ResponseEntity<List<Venda>> findAllVendasFechadas() {

		List<Venda> vendas = vendaService.findAllVendasFechadas();

		ResponseEntity<List<Venda>> response = new ResponseEntity<>(vendas, HttpStatus.OK);

		return response;
	}

	@PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Venda> save(@RequestBody Venda venda) {

		ResponseEntity<Venda> response = null;

		try {
			venda = vendaService.save(venda);
			response = new ResponseEntity<>(venda, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		return response;

	}
	
	@PostMapping(path = "/saveSemDecrementar", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Venda> savePedido(@RequestBody Venda venda) {

		ResponseEntity<Venda> response = null;

		try {
			venda = vendaService.saveSemDecrementar(venda);
			response = new ResponseEntity<>(venda, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		return response;

	}

	@GetMapping(path = "/findByDataVenda", produces = "application/json")
	public ResponseEntity<Venda> findByDataVenda(@RequestParam(value = "dataVenda") String dataVenda) {

		ResponseEntity<Venda> response = null;

		try {
			response = new ResponseEntity<Venda>(vendaService.findByDataVenda(dataVenda), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Venda>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@DeleteMapping(path = "/delete", produces = "application/json")
	public ResponseEntity<Boolean> delete(@RequestParam(value = "id") Long id) {

		ResponseEntity<Boolean> response = null;
		
		try {
			response = new ResponseEntity<>(vendaService.delete(id), HttpStatus.OK);
		}catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		

		return response;
	}

}
