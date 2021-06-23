package br.com.wellington.exception;

public class SenhaInvalidaException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SenhaInvalidaException() {
		super("{campo.senha.invalida}");
	}

}
