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

import br.com.projetoperna.model.Perfil;
import br.com.projetoperna.services.PerfilService;

@RestController()
@RequestMapping("/perfis")
public class PerfilController {

	@Autowired
	private PerfilService perfilService;

	@GetMapping(path = "/findById", produces = "application/json")
	public ResponseEntity<Perfil> getPerfil(@RequestParam("id") Long id) {

		ResponseEntity<Perfil> response = null;

		try {
			response = new ResponseEntity<Perfil>(perfilService.find(id), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Perfil>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Perfil>> findAll() {

		List<Perfil> perfis = perfilService.findAll();

		ResponseEntity<List<Perfil>> response = new ResponseEntity<>(perfis, HttpStatus.OK);

		return response;
	}

	@PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Perfil> save(@RequestBody Perfil perfil) {

		ResponseEntity<Perfil> response = null;

		try {
			perfil = perfilService.save(perfil);
			response = new ResponseEntity<>(perfil, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		return response;

	}

	@GetMapping(path = "/findByNome", produces = "application/json")
	public ResponseEntity<Perfil> findByNome(@RequestParam(value = "nome") String nome) {

		ResponseEntity<Perfil> response = null;

		try {
			response = new ResponseEntity<Perfil>(perfilService.findByNome(nome), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Perfil>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@DeleteMapping(path = "/delete", produces = "application/json")
	public ResponseEntity<Boolean> delete(@RequestParam(value = "id") Long id) {

		ResponseEntity<Boolean> response = null;
		
		try {
			response = new ResponseEntity<>(perfilService.delete(id), HttpStatus.OK);
		}catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		

		return response;
	}

}
