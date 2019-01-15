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

import br.com.projetoperna.model.Categoria;
import br.com.projetoperna.services.CategoriaService;

@RestController()
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping(path = "/findById", produces = "application/json")
	public ResponseEntity<Categoria> getCategoria(@RequestParam("id") Long id) {

		ResponseEntity<Categoria> response = null;

		try {
			response = new ResponseEntity<Categoria>(categoriaService.find(id), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Categoria>> findAll() {

		List<Categoria> perfis = categoriaService.findAll();

		ResponseEntity<List<Categoria>> response = new ResponseEntity<>(perfis, HttpStatus.OK);

		return response;
	}

	@PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {

		ResponseEntity<Categoria> response = null;

		try {
			categoria = categoriaService.save(categoria);
			response = new ResponseEntity<>(categoria, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		return response;

	}

	@GetMapping(path = "/findByNome", produces = "application/json")
	public ResponseEntity<Categoria> findByNome(@RequestParam(value = "nome") String nome) {

		ResponseEntity<Categoria> response = null;

		try {
			response = new ResponseEntity<Categoria>(categoriaService.findByNome(nome), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@DeleteMapping(path = "/delete", produces = "application/json")
	public ResponseEntity<Boolean> delete(@RequestParam(value = "id") Long id) {

		ResponseEntity<Boolean> response = null;
		
		try {
			response = new ResponseEntity<>(categoriaService.delete(id), HttpStatus.OK);
		}catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		

		return response;
	}

}
