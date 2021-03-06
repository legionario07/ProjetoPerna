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

import br.com.projetoperna.model.Marca;
import br.com.projetoperna.services.MarcaService;

@RestController()
@RequestMapping("/marcas")
public class MarcaController {

	@Autowired
	private MarcaService marcaService;

	@GetMapping(path = "/findById", produces = "application/json")
	public ResponseEntity<Marca> getMarca(@RequestParam("id") Long id) {

		ResponseEntity<Marca> response = null;

		try {
			response = new ResponseEntity<Marca>(marcaService.find(id), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Marca>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Marca>> findAll() {

		List<Marca> perfis = marcaService.findAll();

		ResponseEntity<List<Marca>> response = new ResponseEntity<>(perfis, HttpStatus.OK);

		return response;
	}

	@PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Marca> save(@RequestBody Marca marca) {

		ResponseEntity<Marca> response = null;

		try {
			marca = marcaService.save(marca);
			response = new ResponseEntity<>(marca, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		return response;

	}

	@GetMapping(path = "/findByNome", produces = "application/json")
	public ResponseEntity<Marca> findByNome(@RequestParam(value = "nome") String nome) {

		ResponseEntity<Marca> response = null;

		try {
			response = new ResponseEntity<Marca>(marcaService.findByNome(nome), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Marca>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@DeleteMapping(path = "/delete", produces = "application/json")
	public ResponseEntity<Boolean> delete(@RequestParam(value = "id") Long id) {

		ResponseEntity<Boolean> response = null;
		
		try {
			response = new ResponseEntity<>(marcaService.delete(id), HttpStatus.OK);
		}catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		

		return response;
	}

}
