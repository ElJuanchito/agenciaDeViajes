package co.edu.uniquindio.agenciaviajes.exceptions;

import co.edu.uniquindio.agenciaviajes.controllers.Vista;

public class FXMLException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Es el constructo de la clase {@link FXMLException}, se lanza cuando no se
	 * puede cargar un archivo FXML desde la clase {@link Vista}
	 * 
	 * @param msg
	 * @param cause
	 */
	public FXMLException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
