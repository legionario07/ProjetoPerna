package br.com.projetoperna.exceptions;

import org.springframework.http.HttpStatus;

public class ProdutoSubProdutoException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;
	
	public ProdutoSubProdutoException() {
		super();
		this.httpStatus = HttpStatus.CONFLICT;
	}
	
	public ProdutoSubProdutoException(HttpStatus httpStatus) {
		this();
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
