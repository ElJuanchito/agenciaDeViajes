package co.edu.uniquindio.agenciaviajes.controllers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoVista {
	LOGIN("login"), DESTINOS("viewDestinos"), REGISTRO("registration"), PAQUETE_DETAILS("paqueteDetails"),
	MENU_PRINCIPAL("menuPrincipal"), CREAR_RESERVA("crearReserva"), REGISTRO_GUIA("registroGuia");

	private String ruta;
}
