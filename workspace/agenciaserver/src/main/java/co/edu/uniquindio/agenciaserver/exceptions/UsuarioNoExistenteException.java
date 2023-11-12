package co.edu.uniquindio.agenciaserver.exceptions;

public class UsuarioNoExistenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsuarioNoExistenteException(String msg) {
		super(msg);
	}

}
