package br.com.wellington.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "produtos")
public class Produto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduto;
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;
	@NotEmpty(message = "{campo.descricao.obrigatorio}")
	private String descricao;
	@NotNull(message = "{campo.preco.obrigatorio}")
	private Float preco;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
	@JsonIgnore
	private List<ItemPedido> itensPedido;


	public Produto(String nome, String descricao, Float preco, List<ItemPedido> itensPedido) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.itensPedido = itensPedido;
	}

	

}
