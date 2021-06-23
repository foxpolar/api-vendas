package br.com.wellington.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException() {
		super("{campo.pedido.nao-encontrado}");

	}

}
