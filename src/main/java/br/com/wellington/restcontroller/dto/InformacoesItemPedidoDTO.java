package br.com.wellington.restcontroller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesItemPedidoDTO {

	private String descricaoProduto;
	private Float precoUnitario;
	private Integer quantidade;

}
