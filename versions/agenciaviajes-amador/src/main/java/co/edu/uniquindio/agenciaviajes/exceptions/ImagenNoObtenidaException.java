package co.edu.uniquindio.agenciaviajes.exceptions;

import co.edu.uniquindio.agenciaviajes.model.Imagen;

public class ImagenNoObtenidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Es el constructor de la clase {@link ImagenNoObtenidaException}, se lanza
	 * cuando al crear una instancia de {@link Imagen} no se puede obtener esta
	 * misma
	 * 
	 * @param msg
	 * @param cause
	 */
	public ImagenNoObtenidaException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
