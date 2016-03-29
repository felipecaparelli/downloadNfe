package br.com.wro.nfe.exception;

public class ProcessoNfeException extends Exception {

	private static final long serialVersionUID = -6176838505886571506L;
	
	public ProcessoNfeException() { super(); }
	public ProcessoNfeException(String message) { super(message); }
	public ProcessoNfeException(String message, Throwable cause) { super(message, cause); }
	public ProcessoNfeException(Throwable cause) { super(cause); }

}
