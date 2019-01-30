package br.com.projetoperna.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@JsonAutoDetect
@Entity
@Table(name ="produto")
public class Produto extends Mercadoria implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2923242432699616549L;
	

	@Column(name = "valor_compra")
	private BigDecimal valorCompra;
	@ManyToOne
	private Marca marca;
	@Column(name = "ean_pai")
	private String eanPai;
	@Column(name = "is_sub_produto")
	private boolean isSubProduto;
	@Column(name = "qtde_sub_produto")
	private Integer qtdeSubProduto;
	
	public Produto(Long id) {
		this();
		this.setId(id);
	}
	
	public Produto() {
		//Por Default o produto inicia-se como Ativo no cadastro
		super();
		this.marca = new Marca();
	}

	
	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}


	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public void decrementarProduto(Integer valorADecrementar) {
		if(getQtde()<=valorADecrementar) {
			setQtde(0);
		}else {
			setQtde(getQtde() - valorADecrementar);
		}
	}


	public String getEanPai() {
		return eanPai;
	}

	public void setEanPai(String eanPai) {
		this.eanPai = eanPai;
	}

	public boolean isSubProduto() {
		return isSubProduto;
	}

	public void setSubProduto(boolean isSubProduto) {
		this.isSubProduto = isSubProduto;
	}

	public Integer getQtdeSubProduto() {
		return qtdeSubProduto;
	}

	public void setQtdeSubProduto(Integer qtdeSubProduto) {
		this.qtdeSubProduto = qtdeSubProduto;
	}

}
