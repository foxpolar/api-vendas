package br.com.wellington.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "itensPedidos")
public class ItemPedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idItemPedido;
	private Integer quantidade;

	@ManyToOne(optional = false)
	@JoinColumn(name = "pedido", referencedColumnName = "idPedido")
	private Pedido pedido;

	@ManyToOne(optional = false)
	@JoinColumn(name = "produto", referencedColumnName = "idProduto")
	private Produto produto;

	public ItemPedido(Integer quantidade, Pedido pedido, Produto produto) {
		super();
		this.quantidade = quantidade;
		this.pedido = pedido;
		this.produto = produto;
	}

}