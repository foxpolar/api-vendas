package br.com.wellington.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.wellington.exception.PedidoNaoEncontradoException;
import br.com.wellington.exception.RegraNegocioException;
import br.com.wellington.models.Cliente;
import br.com.wellington.models.ItemPedido;
import br.com.wellington.models.Pedido;
import br.com.wellington.models.Produto;
import br.com.wellington.models.enums.StatusPedido;
import br.com.wellington.repository.ClienteRepository;
import br.com.wellington.repository.ItemPedidoRepository;
import br.com.wellington.repository.PedidoRepository;
import br.com.wellington.repository.ProdutoRepository;
import br.com.wellington.restcontroller.dto.ItemPedidoDTO;
import br.com.wellington.restcontroller.dto.PedidoDTO;
import br.com.wellington.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		Cliente cliente = clienteRepository.findById(dto.getCliente())
				.orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.REALIZADO);
		List<ItemPedido> itemPedidos = converterItems(pedido, dto.getItems());
		pedidoRepository.save(pedido);
		itemPedidoRepository.saveAll(itemPedidos);
		pedido.setItensPedido(itemPedidos);
		return pedido;
	}

	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
		if (items.isEmpty()) {
			throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
		}

		return items.stream().map(dto -> {
			Long idProduto = dto.getProduto();
			Produto produto = produtoRepository.findById(idProduto)
					.orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));

			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(dto.getQuantidade());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			return itemPedido;
		}).collect(Collectors.toList());
	}

	@Override
	public Optional<Pedido> obterPedidoCompleto(Long idPedido) {
		return pedidoRepository.findByIdPedidoFetchItensPedido(idPedido);
	}

	@Override
	@Transactional
	public void atualizarStatus(Long idPedido, StatusPedido statusPedido) {
		pedidoRepository.findById(idPedido)
		.map( pedido -> {
			pedido.setStatus(statusPedido);
			return pedidoRepository.save(pedido);
		}).orElseThrow( () -> new PedidoNaoEncontradoException());
		
	}

	

}
