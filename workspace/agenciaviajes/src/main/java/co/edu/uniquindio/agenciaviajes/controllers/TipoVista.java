package co.edu.uniquindio.agenciaviajes.controllers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoVista {
	LOGIN("login"), DESTINOS("viewDestinos"), REGISTRO("registration"), PAQUETE_DETAILS("paqueteDetails"),
	MENU_PRINCIPAL_CLIENTE("menuPrincipal"), CREAR_RESERVA("crearReserva"), REGISTRO_GUIA("registroGuia"),
	MENU_PRINCIPAL_ADMIN("menuPrincipalAdmins"), NONE("none"), PAQUETES("viewPaquetes"),
	BUSQUEDA_AVANZADA("busquedaAvanzada"), GUIAS("viewGuias"), VER_PERFIL("VerPerfil");

	private String ruta;
}
