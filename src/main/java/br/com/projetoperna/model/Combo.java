package br.com.projetoperna.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "combo")
public class Combo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @ManyToMany(cascade=CascadeType.REFRESH)
	private List<Produto> produtos;

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
	
	public Combo(Long id) {
		this();
		this.setId(id);
	}
	
	public Combo() {
		this.produtos = new ArrayList<Produto>();
		this.isAtivo = true;
		this.unidadeDeMedida = new UnidadeDeMedida();
		this.categoria = new Categoria();
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


	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	
	

}
