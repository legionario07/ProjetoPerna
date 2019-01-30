package br.com.projetoperna.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//@JsonAutoDetect
@Entity
@Table(name ="produto")
public class Produto implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2923242432699616549L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;
	private String descricao;
	@Column(name = "valor_venda")
	private BigDecimal valorVenda;
	private Integer qtde;
	@Column(name = "qtde_minima")
	private Integer qtdeMinima;
	@Column(unique = true)
	private String ean;
	@Column(name="is_ativo")
	private boolean isAtivo;
	@OneToOne
	private UnidadeDeMedida unidadeDeMedida;
	@OneToOne
	private Categoria categoria;
	@Column(name ="data_cadastro")
	private Long dataCadastro;
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
		this.marca = new Marca();
		this.isAtivo = true;
		this.unidadeDeMedida = new UnidadeDeMedida();
		this.categoria = new Categoria();
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
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public Integer getQtdeMinima() {
		return qtdeMinima;
	}

	public void setQtdeMinima(Integer qtdeMinima) {
		this.qtdeMinima = qtdeMinima;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public boolean isAtivo() {
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public UnidadeDeMedida getUnidadeDeMedida() {
		return unidadeDeMedida;
	}

	public void setUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
		this.unidadeDeMedida = unidadeDeMedida;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Long dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
