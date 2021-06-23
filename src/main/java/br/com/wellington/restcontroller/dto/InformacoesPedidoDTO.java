package br.com.wellington.restcontroller.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO {
	private Long codigo;
	private String cpf;
	private String nomeCliente;
	private Float total;
	private String data_pedido;
	private String status;
	private List<InformacoesItemPedidoDTO> items;
	
}
