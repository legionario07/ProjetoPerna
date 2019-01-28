package br.com.projetoperna.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="valor_total")
	private BigDecimal valorTotal;
	@OneToOne
	private Mercadoria produto;
	private Integer total;
	private BigDecimal desconto;

	
	
	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public Pedido(BigDecimal valorTotal, Mercadoria produto, Integer total) {
		super();
		this.valorTotal = valorTotal;
		this.produto = produto;
		this.total = total;
	}

	public Mercadoria getProduto() {
		return produto;
	}

	public void setProduto(Mercadoria produto) {
		this.produto = produto;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Pedido(Long id) {
		this();
		this.id = id;
	}
	
	public Pedido() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	

}
