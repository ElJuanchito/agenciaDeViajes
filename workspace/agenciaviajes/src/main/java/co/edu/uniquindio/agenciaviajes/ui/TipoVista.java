package co.edu.uniquindio.agenciaviajes.ui;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoVista {
	LOGIN("login"), DESTINOS("viewDestinos"), REGISTRO("registration"), PAQUETE_DETAILS("paqueteDetails"),
	MENU_PRINCIPAL("menuPrincipal");

	private String ruta;
}
