package br.com.wellington.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import br.com.wellington.models.Cliente;
import br.com.wellington.repository.ClienteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController // todos os métodos já vao ter o @ResponseBody
@RequestMapping("/api/clientes")
@Api("Api de Clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	// public ClienteController(ClienteRepository clienteRepository) {
	// super();
	// this.clienteRepository = clienteRepository;
	// }

	// @RequestMapping(value = "/hello/{nome}",
	// method = RequestMethod.GET)//consumes = {"application/json, application.xml"}
	// // produces = {"application/json, application.xml"}
	// public String helloCliente(@PathVariable("nome") String nomeCliente) {//,
	// @ResponseBody Cliente cliente
	// return String.format("Hello %s",nomeCliente);
	// }

	@GetMapping(value = "{id}")
	@ApiOperation("Obter detalhes do cliente.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Cliente encontrado."),
			@ApiResponse(code = 404, message = "Cliente não encontrado.") })
	public Cliente getIdCliente(@PathVariable("id") @ApiParam("Id do cliente") Long idCliente) {
		return clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Salva cliente")
	@ApiResponses({ @ApiResponse(code = 201, message = "Cliente salvo com sucesso."),
			@ApiResponse(code = 400, message = "Erro na validação do cliente.") })
	public Cliente save(@RequestBody @Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("{campo.cliente.update}")
	@ApiResponses({ @ApiResponse(code = 204, message = "Cliente atualizado com sucesso."),
			@ApiResponse(code = 404, message = "Erro na validação do cliente.") })
	public void update(@PathVariable("id") Long idCliente, @RequestBody @Valid Cliente cliente) {
		clienteRepository.findById(idCliente).map(clienteExistente -> {
			cliente.setIdCliente(clienteExistente.getIdCliente());
			clienteRepository.save(cliente);
			return clienteExistente;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("{campo.cliente.delete}")
	@ApiResponses({ @ApiResponse(code = 204, message = "Cliente deletado com sucesso."),
			@ApiResponse(code = 404, message = "Erro na validação do cliente.") })
	public void delete(@PathVariable Long id) {
		clienteRepository.findById(id).map(cliente -> {
			clienteRepository.delete(cliente);
			return cliente;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}

	@GetMapping
	@ApiOperation("Pesquisa por cliente com filtro")
	@ApiResponses({ @ApiResponse(code = 200, message = "Executado com sucesso."),
			 })
	public List<Cliente> find(Cliente filtro) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(filtro, exampleMatcher);
		return clienteRepository.findAll(example);
	}
}
