package br.com.projetoperna.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.projetoperna.repository.ProdutoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ProdutoServiceTest {
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Test
	public void findByEanPaiAndIsSubProduto() {
		
		
		String eanPai = "123456789";
		Boolean isSubProduto = true;
		
		System.out.println(produtoRepo.findByEanAndIsSubProduto(eanPai, isSubProduto));
		
		
	}
	
}
