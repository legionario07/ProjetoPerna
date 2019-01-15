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

import br.com.projetoperna.model.UnidadeDeMedida;
import br.com.projetoperna.services.UnidadeDeMedidaService;

@RestController()
@RequestMapping("/unidadesDeMedidas")
public class UnidadeDeMedidaController {

	@Autowired
	private UnidadeDeMedidaService unidadeDeMedidaService;

	@GetMapping(path = "/findById", produces = "application/json")
	public ResponseEntity<UnidadeDeMedida> getUnidadeDeMedida(@RequestParam("id") Long id) {

		ResponseEntity<UnidadeDeMedida> response = null;

		try {
			response = new ResponseEntity<UnidadeDeMedida>(unidadeDeMedidaService.find(id), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<UnidadeDeMedida>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<UnidadeDeMedida>> findAll() {

		List<UnidadeDeMedida> perfis = unidadeDeMedidaService.findAll();

		ResponseEntity<List<UnidadeDeMedida>> response = new ResponseEntity<>(perfis, HttpStatus.OK);

		return response;
	}

	@PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<UnidadeDeMedida> save(@RequestBody UnidadeDeMedida unidadeDeMedida) {

		ResponseEntity<UnidadeDeMedida> response = null;

		try {
			unidadeDeMedida = unidadeDeMedidaService.save(unidadeDeMedida);
			response = new ResponseEntity<>(unidadeDeMedida, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		return response;

	}

	@GetMapping(path = "/findByTipo", produces = "application/json")
	public ResponseEntity<UnidadeDeMedida> findByTipo(@RequestParam(value = "tipo") String tipo) {

		ResponseEntity<UnidadeDeMedida> response = null;

		try {
			response = new ResponseEntity<UnidadeDeMedida>(unidadeDeMedidaService.findByTipo(tipo), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<UnidadeDeMedida>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@DeleteMapping(path = "/delete", produces = "application/json")
	public ResponseEntity<Boolean> delete(@RequestParam(value = "id") Long id) {

		ResponseEntity<Boolean> response = null;
		
		try {
			response = new ResponseEntity<>(unidadeDeMedidaService.delete(id), HttpStatus.OK);
		}catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		

		return response;
	}

}
