package co.edu.uniquindio.agenciaviajes.controllers;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InfoVista {
	@EqualsAndHashCode.Include
	TipoVista tipoVista;
	@EqualsAndHashCode.Include
	Object dato;
}
