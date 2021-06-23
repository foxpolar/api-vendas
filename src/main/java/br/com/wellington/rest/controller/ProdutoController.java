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
import br.com.wellington.models.Produto;
import br.com.wellington.repository.ProdutoRepository;

@RestController // todos os métodos já vao ter o @ResponseBody
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	// public ProdutoController(ProdutoRepository ProdutoRepository) {
	// super();
	// this.ProdutoRepository = ProdutoRepository;
	// }

	// @RequestMapping(value = "/hello/{nome}",
	// method = RequestMethod.GET)//consumes = {"application/json, application.xml"}
	// // produces = {"application/json, application.xml"}
	// public String helloProduto(@PathVariable("nome") String nomeProduto) {//,
	// @ResponseBody Produto Produto
	// return String.format("Hello %s",nomeProduto);
	// }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto save(@RequestBody @Valid Produto produto) {
		return produtoRepository.save(produto);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") Long idProduto, @RequestBody @Valid Produto produto) {
		produtoRepository.findById(idProduto).map(produtoExistente -> {
			produto.setIdProduto(produtoExistente.getIdProduto());
			produtoRepository.save(produto);
			return produtoExistente;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		produtoRepository.findById(id).map(Produto -> {
			produtoRepository.delete(Produto);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}

	@GetMapping(value = "{id}")
	public Produto getIdProduto(@PathVariable("id") Long idProduto) {
		return produtoRepository.findById(idProduto)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}

	@SuppressWarnings("unchecked")
	@GetMapping
	public List<Produto> find(Produto filtro) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		@SuppressWarnings("rawtypes")
		Example example = Example.of(filtro, exampleMatcher);
		return produtoRepository.findAll(example);

	}

}
