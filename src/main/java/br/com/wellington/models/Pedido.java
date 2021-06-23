package br.com.wellington.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.wellington.models.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;

	//@Temporal(TemporalType.DATE)
	private LocalDate dataPedido;
	
	private Float total;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente", referencedColumnName = "idCliente")
	private Cliente cliente;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
	private List<ItemPedido> itensPedido;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusPedido status;

	public Pedido(LocalDate dataPedido, float total, Cliente cliente, List<ItemPedido> itensPedido) {
		super();
		this.dataPedido = dataPedido;
		this.total = total;
		this.cliente = cliente;
		this.itensPedido = itensPedido;
	}

	

}
