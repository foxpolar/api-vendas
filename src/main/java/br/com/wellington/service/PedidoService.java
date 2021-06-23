package br.com.wellington.service;

import java.util.Optional;

import br.com.wellington.models.Pedido;
import br.com.wellington.models.enums.StatusPedido;
import br.com.wellington.restcontroller.dto.PedidoDTO;

public interface PedidoService {
	Pedido salvar(PedidoDTO dto);
	Optional<Pedido> obterPedidoCompleto(Long idPedido);
	void atualizarStatus(Long idPedido, StatusPedido statusPedido);

}
