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

import br.com.projetoperna.model.Combo;
import br.com.projetoperna.services.ComboService;

@RestController()
@RequestMapping("/combos")
public class ComboController {

	@Autowired
	private ComboService comboService;

	@GetMapping(path = "/findById", produces = "application/json")
	public ResponseEntity<Combo> getCombo(@RequestParam("id") Long id) {

		ResponseEntity<Combo> response = null;

		try {
			response = new ResponseEntity<Combo>(comboService.find(id), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Combo>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Combo>> findAll() {

		List<Combo> perfis = comboService.findAll();

		ResponseEntity<List<Combo>> response = new ResponseEntity<>(perfis, HttpStatus.OK);

		return response;
	}

	@PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Combo> save(@RequestBody Combo combo) {

		ResponseEntity<Combo> response = null;

		try {
			combo = comboService.save(combo);
			response = new ResponseEntity<>(combo, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		return response;

	}

	@GetMapping(path = "/findByNome", produces = "application/json")
	public ResponseEntity<Combo> findByNome(@RequestParam(value = "nome") String nome) {

		ResponseEntity<Combo> response = null;

		try {
			response = new ResponseEntity<Combo>(comboService.findByNome(nome), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Combo>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@DeleteMapping(path = "/delete", produces = "application/json")
	public ResponseEntity<Boolean> delete(@RequestParam(value = "id") Long id) {

		ResponseEntity<Boolean> response = null;
		
		try {
			response = new ResponseEntity<>(comboService.delete(id), HttpStatus.OK);
		}catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		

		return response;
	}

}
