package br.com.wellington.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.wellington.exception.PedidoNaoEncontradoException;
import br.com.wellington.exception.RegraNegocioException;
import br.com.wellington.exception.SenhaInvalidaException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleRegraNegociosException(RegraNegocioException ex) {
		String menssagemErro = ex.getMessage();
		return new ApiErrors(menssagemErro);
	}

	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors pedidoNotFoundException(PedidoNaoEncontradoException ex) {
		return new ApiErrors(ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
			List<String> erros =  ex.getBindingResult().getAllErrors().stream().map( error ->  error.getDefaultMessage()).collect(Collectors.toList());
			return new ApiErrors(erros);
	}
	///
	@ExceptionHandler(SenhaInvalidaException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleMethodInvalidPassword(SenhaInvalidaException ex) {
		return new ApiErrors(ex.getMessage());
	}
	
	
	

}
