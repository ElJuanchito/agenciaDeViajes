package co.edu.uniquindio.agenciaviajes.exceptions;

public class ClienteNoExistenteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClienteNoExistenteException(String msg) {
		super(msg);
	}

}
