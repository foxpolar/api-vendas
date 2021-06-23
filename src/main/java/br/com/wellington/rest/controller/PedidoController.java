package br.com.wellington.rest.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.wellington.models.ItemPedido;
import br.com.wellington.models.Pedido;
import br.com.wellington.models.enums.StatusPedido;
import br.com.wellington.restcontroller.dto.AtualizacaoStatusPedidoDTO;
import br.com.wellington.restcontroller.dto.InformacoesItemPedidoDTO;
import br.com.wellington.restcontroller.dto.InformacoesPedidoDTO;
import br.com.wellington.restcontroller.dto.PedidoDTO;
import br.com.wellington.service.PedidoService;

@RestController // todos os métodos já vao ter o @ResponseBody
@RequestMapping("/api/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long save(@RequestBody @Valid PedidoDTO dto) {
		Pedido pedido = pedidoService.salvar(dto);
		return pedido.getIdPedido();
	}

	@GetMapping("{id}")
	public InformacoesPedidoDTO getById(@PathVariable("id") Long idPedido) {
		return pedidoService.obterPedidoCompleto(idPedido).map(p -> converterPedido(p))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
	}

	private InformacoesPedidoDTO converterPedido(Pedido pedido) {
		return InformacoesPedidoDTO.builder().codigo(pedido.getIdPedido())
				.nomeCliente(pedido.getCliente().getNome())
				.data_pedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
				.cpf(pedido.getCliente().getCpf()).total(pedido.getTotal())
				.status(pedido.getStatus().name())
				.items(converterItemPedido(pedido.getItensPedido())).build();

	} 

	private List<InformacoesItemPedidoDTO> converterItemPedido(List<ItemPedido> itens) {
		if (CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}

		return itens.stream()
				.map(item -> InformacoesItemPedidoDTO.builder().descricaoProduto(item.getProduto().getDescricao())
						.precoUnitario(item.getProduto().getPreco()).quantidade(item.getQuantidade()).build())
				.collect(Collectors.toList());
	}
	
	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Long id,  @RequestBody AtualizacaoStatusPedidoDTO dto) {
		String novoStatus = dto.getNovoStatus();
		pedidoService.atualizarStatus(id, StatusPedido.valueOf(novoStatus));
	}

}
