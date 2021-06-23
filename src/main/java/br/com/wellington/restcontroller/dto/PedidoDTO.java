package br.com.wellington.restcontroller.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.wellington.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * {
    "cliente" : 1,
    "total" : 100.00,
    "itens" : [
        {
            "produto" : 1,
            "quantidade": 5

        }
    ]
}
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {// Data Transfer Object
	@NotNull(message = "{campo.codigo-cliente.obrigatorio}")
	private Long cliente;
	@NotNull(message = "{campo.total-pedido.obrigatorio}")
	private Float total;
	@NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
	private List<ItemPedidoDTO> items;

}
