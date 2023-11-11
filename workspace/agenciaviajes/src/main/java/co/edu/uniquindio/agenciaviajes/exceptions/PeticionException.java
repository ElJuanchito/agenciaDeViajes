package co.edu.uniquindio.agenciaviajes.exceptions;

/**
 * Es la clase {@link PeticionException}, se lanza cuando al tratar de realizar
 * una peticion al socket no se puede realizar
 */
public class PeticionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Es el constructor de la clase {@link PeticionException}, se lanza cuando al
	 * tratar de realizar una peticion al socket no se puede realizar
	 * 
	 * @param msg
	 */
	public PeticionException(String msg) {
		super(msg);
	}

}
