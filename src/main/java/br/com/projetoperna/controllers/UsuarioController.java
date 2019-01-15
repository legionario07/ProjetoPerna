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

import br.com.projetoperna.model.Usuario;
import br.com.projetoperna.services.UsuarioService;

@RestController()
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping(path = "/findById", produces = "application/json")
	public ResponseEntity<Usuario> getUsuario(@RequestParam("id") Long id) {

		ResponseEntity<Usuario> response = null;

		try {
			response = new ResponseEntity<Usuario>(usuarioService.find(id), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Usuario>> findAll() {

		List<Usuario> perfis = usuarioService.findAll();

		ResponseEntity<List<Usuario>> response = new ResponseEntity<>(perfis, HttpStatus.OK);

		return response;
	}

	@PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {

		ResponseEntity<Usuario> response = null;

		try {
			usuario = usuarioService.save(usuario);
			response = new ResponseEntity<>(usuario, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		return response;

	}

	@GetMapping(path = "/findByUsuario", produces = "application/json")
	public ResponseEntity<Usuario> findByUsuario(@RequestParam(value = "usuario") String usuario) {

		ResponseEntity<Usuario> response = null;

		try {
			response = new ResponseEntity<Usuario>(usuarioService.findByUsuario(usuario), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@PostMapping(path="/login",produces="application/json")
	public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
		
		ResponseEntity<Usuario> response = null;
		
		try {
			response = new ResponseEntity<>(usuarioService.findByUsuarioAndSenha(usuario.getUsuario(), usuario.getSenha()), HttpStatus.OK);
		}catch(Exception e) {
			response = new ResponseEntity<Usuario>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		return response;
	}

	@DeleteMapping(path = "/delete", produces = "application/json")
	public ResponseEntity<Boolean> delete(@RequestParam(value = "id") Long id) {

		ResponseEntity<Boolean> response = null;

		try {
			response = new ResponseEntity<>(usuarioService.delete(id), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		return response;
	}

}
