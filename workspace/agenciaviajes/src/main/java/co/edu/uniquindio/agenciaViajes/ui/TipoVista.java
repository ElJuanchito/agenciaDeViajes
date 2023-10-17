package co.edu.uniquindio.agenciaViajes.ui;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoVista {
	INICIO("login"), DESTINOS("viewDestinos");

	private String ruta;
}
