package br.com.projetoperna.services;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.projetoperna.model.Categoria;
import br.com.projetoperna.model.Combo;
import br.com.projetoperna.model.Marca;
import br.com.projetoperna.model.Produto;
import br.com.projetoperna.model.UnidadeDeMedida;
import br.com.projetoperna.repository.ComboRepository;
import br.com.projetoperna.repository.ProdutoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ComboServiceTest {

	@Autowired
	private ComboRepository comboRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Test
	public void save() {
		
		
		
		Produto produto = new Produto();
		produto.setCategoria(new Categoria(1l));
		produto.setUnidadeDeMedida(new UnidadeDeMedida(1l));
		produto.setMarca(new Marca(1l));
		produto.setAtivo(true);
		produto.setDescricao("TESTE");
		produto.setEan("QEWRQ");
		produto.setNome("teste");
		produto.setQtde(223);
		produto.setDataCadastro(Calendar.getInstance().getTimeInMillis());
		produto.setValorCompra(BigDecimal.TEN);
		
		produtoRepo.save(produto);

		
		
		Combo combo = new Combo();
		
		combo.setAtivo(true);
		combo.setDataCadastro(Calendar.getInstance().getTimeInMillis());
		combo.setDescricao("Teste");
		combo.setNome("Teste");
		combo.setEan("EASF");
		combo.setQtde(10);
		
		
		
		
		
		
	}
	
}
