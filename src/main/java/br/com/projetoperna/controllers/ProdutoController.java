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

import br.com.projetoperna.exceptions.ProdutoSubProdutoException;
import br.com.projetoperna.model.Produto;
import br.com.projetoperna.services.ProdutoService;

@RestController()
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping(path = "/findById", produces = "application/json")
	public ResponseEntity<Produto> getProduto(@RequestParam("id") Long id) {

		ResponseEntity<Produto> response = null;

		try {
			response = new ResponseEntity<Produto>(produtoService.find(id), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Produto>> findAll() {

		List<Produto> perfis = produtoService.findAll();

		ResponseEntity<List<Produto>> response = new ResponseEntity<>(perfis, HttpStatus.OK);

		return response;
	}

	@PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Produto> save(@RequestBody Produto produto) {

		ResponseEntity<Produto> response = null;
		
		try {
			produto = produtoService.save(produto);
			response = new ResponseEntity<>(produto, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		return response;

	}

	@GetMapping(path = "/findByNome", produces = "application/json")
	public ResponseEntity<Produto> findByNome(@RequestParam(value = "nome") String nome) {

		ResponseEntity<Produto> response = null;

		try {
			response = new ResponseEntity<Produto>(produtoService.findByNome(nome), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}

		return response;

	}
	
	@GetMapping(path = "/findByEanAndIsSubProduto", produces = "application/json")
	public ResponseEntity<Produto> findByEanAndIsSubProduto(@RequestParam(value = "eanPai") String eanPai, 
			@RequestParam(value = "isSubProduto") Boolean isSubProduto) {

		ResponseEntity<Produto> response = null;

		try {
			response = new ResponseEntity<Produto>(produtoService.findByEanAndIsSubProduto(eanPai, isSubProduto), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}

		return response;

	}
	
	@GetMapping(path = "/findByEanPaiAndIsSubProduto", produces = "application/json")
	public ResponseEntity<Produto> findByEanPaiAndIsSubProduto(@RequestParam(value = "eanPai") String eanPai, 
			@RequestParam(value = "isSubProduto") Boolean isSubProduto) {

		ResponseEntity<Produto> response = null;

		try {
			response = new ResponseEntity<Produto>(produtoService.findByEanPaiAndIsSubProduto(eanPai, isSubProduto), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}

		return response;

	}
	
	@GetMapping(path = "/findByEanPai", produces = "application/json")
	public ResponseEntity<Produto> findByEanPai(@RequestParam(value = "eanPai") String eanPai) {

		ResponseEntity<Produto> response = null;

		try {
			response = new ResponseEntity<Produto>(produtoService.findByEanPai(eanPai), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}

		return response;

	}
	
	@GetMapping(path = "/findByCategoriaId", produces = "application/json")
	public ResponseEntity<List<Produto>> findByCategoraId(@RequestParam(value = "categoriaId") Long categoriaId) {

		ResponseEntity<List<Produto>> response = null;

		try {
			response = new ResponseEntity<List<Produto>>(produtoService.findByCategoriaId(categoriaId), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<List<Produto>>(HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@DeleteMapping(path = "/delete", produces = "application/json")
	public ResponseEntity<Boolean> delete(@RequestParam(value = "id") Long id) {

		ResponseEntity<Boolean> response = null;
		
		try {
			response = new ResponseEntity<>(produtoService.delete(id), HttpStatus.OK);
		}catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		

		return response;
	}


}
