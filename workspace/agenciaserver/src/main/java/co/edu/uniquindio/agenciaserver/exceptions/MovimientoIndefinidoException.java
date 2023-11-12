package co.edu.uniquindio.agenciaserver.exceptions;

/**
 * Es la clase {@link MovimientoIndefinidoException}, se lanza cuando hay un
 * movimiento de las vistas que no se puede hacer, como por ejemplo ir antes de
 * la vista inicial
 */
public class MovimientoIndefinidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Es el constructor de la clase {@link MovimientoIndefinidoException}, se lanza
	 * cuando hay un movimiento de las vistas que no se puede hacer, como por
	 * ejemplo ir antes de la vista inicial
	 * 
	 * @param msg
	 */
	public MovimientoIndefinidoException(String msg) {
		super(msg);
	}
}
