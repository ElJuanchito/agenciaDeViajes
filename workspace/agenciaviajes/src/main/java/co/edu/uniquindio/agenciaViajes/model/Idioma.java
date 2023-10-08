/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Idioma {
	INGLES("Ingles"), ESPANOL("Español"), PORTUGUES("Portugues"), FRANCES("Frances"), ITALIANO("Italiano"),
	JAPONES("Japones"), MANDARIN("Mandarin"), COREANO("Coreano"), RUSO("Ruso"), ALEMAN("Aleman"), HINDU("Hindu"),
	ARABE("Arabe");

	@Getter
	private final String idioma;

	public static String[] stringValues() {
		Idioma[] values = values();
		String[] stringValues = new String[values.length];
		for (int i = 0; i < values.length; i++)
			stringValues[i] = values[i].getIdioma();
		return stringValues;
	}

	public static Idioma getIdiomaByString(String estadoString) {
		Idioma[] values = values();
		for (Idioma estado : values)
			if (estado.getIdioma().equals(estadoString))
				return estado;
		return null;
	}
}
