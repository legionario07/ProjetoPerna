package br.com.projetoperna.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.projetoperna.utils.JsonCalendarDeserializer;

@Entity
@Table(name ="produto")
public class Produto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	private String descricao;
	@Column(name = "valor_compra")
	private BigDecimal valorCompra;
	@Column(name = "valor_venda")
	private BigDecimal valorVenda;
	private Integer qtde;
	@Column(name = "qtde_minima")
	private Integer qtdeMinima;
	@Column(unique = true)
	private String EAN;
	@Column(name="is_ativo")
	private boolean isAtivo;
	@OneToOne
	private UnidadeDeMedida unidadeDeMedida;
	@Column(name ="data_cadastro")
	@Temporal(TemporalType.DATE)
	@JsonDeserialize(using = JsonCalendarDeserializer.class)
	private Calendar dataCadastro;
	@ManyToOne
	private Marca marca;
	
	public Produto(Long id) {
		this();
		this.id = id;
	}
	
	public Produto() {
		//Por Default o produto inicia-se como Ativo no cadastro
		this.isAtivo = true;
		this.unidadeDeMedida = new UnidadeDeMedida();
		this.marca = new Marca();
		this.dataCadastro = Calendar.getInstance();
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
		this.nome = nome.toUpperCase().trim();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase().trim();
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
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

	public String getEAN() {
		return EAN;
	}

	public void setEAN(String eAN) {
		EAN = eAN;
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

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", valorCompra=" + valorCompra
				+ ", valorVenda=" + valorVenda + ", qtde=" + qtde + ", qtdeMinima=" + qtdeMinima + ", EAN=" + EAN
				+ ", isAtivo=" + isAtivo + ", unidadeDeMedida=" + unidadeDeMedida + "]";
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public void decrementarProduto(Integer valorADecrementar) {
		if(qtde<=valorADecrementar) {
			qtde = 0;
		}else {
			qtde = qtde - valorADecrementar;
		}
	}

}
