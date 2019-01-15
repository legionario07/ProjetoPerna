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

import br.com.projetoperna.model.Configuracao;
import br.com.projetoperna.services.ConfiguracaoService;

@RestController()
@RequestMapping("/configuracoes")
public class ConfiguracaoController {

	@Autowired
	private ConfiguracaoService configuracaoService;

	@GetMapping(path = "/findById", produces = "application/json")
	public ResponseEntity<Configuracao> getConfiguracao(@RequestParam("id") Long id) {

		ResponseEntity<Configuracao> response = null;

		try {
			response = new ResponseEntity<Configuracao>(configuracaoService.find(id), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Configuracao>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Configuracao>> findAll() {

		List<Configuracao> perfis = configuracaoService.findAll();

		ResponseEntity<List<Configuracao>> response = new ResponseEntity<>(perfis, HttpStatus.OK);

		return response;
	}

	@PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Configuracao> save(@RequestBody Configuracao configuracao) {

		ResponseEntity<Configuracao> response = null;

		try {
			configuracao = configuracaoService.save(configuracao);
			response = new ResponseEntity<>(configuracao, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		return response;

	}

	@GetMapping(path = "/findByPropriedade", produces = "application/json")
	public ResponseEntity<Configuracao> findByPropriedade(@RequestParam(value = "propriedade") String propriedade) {

		ResponseEntity<Configuracao> response = null;

		try {
			response = new ResponseEntity<Configuracao>(configuracaoService.findByPropriedade(propriedade), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Configuracao>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@DeleteMapping(path = "/delete", produces = "application/json")
	public ResponseEntity<Boolean> delete(@RequestParam(value = "id") Long id) {

		ResponseEntity<Boolean> response = null;
		
		try {
			response = new ResponseEntity<>(configuracaoService.delete(id), HttpStatus.OK);
		}catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		

		return response;
	}

}
