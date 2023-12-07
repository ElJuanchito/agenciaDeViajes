package co.edu.uniquindio.agenciaviajes.controllers;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Setter
@Getter
public class Peticion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TipoPeticion tipo;
	Object peticion;
}
