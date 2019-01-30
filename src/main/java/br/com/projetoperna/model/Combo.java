package br.com.projetoperna.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "combo")
public class Combo extends Mercadoria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@OneToMany(cascade = CascadeType.ALL)
	private List<Produto> produtos;

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Combo(Long id) {
		this();
		this.setId(id);
	}
	
	public Combo() {
		this.produtos = new ArrayList<Produto>();
	}

	

}
