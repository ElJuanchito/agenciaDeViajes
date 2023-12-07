package co.edu.uniquindio.agenciaviajes.controllers;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Peticion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TipoPeticion tipo;
	Object peticion;
}
