/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import lombok.Getter;

/**
 * 
 * @author ElJuancho
 */
public enum Idioma {
	INGLES("Ingles"), ESPANOL("Español"), PORTUGUES("Portugues"), FRANCES("Frances"), ITALIANO("Italiano"),
	JAPONES("Japones"), MANDARIN("Mandarin"), COREANO("Coreano"), RUSO("Ruso"), ALEMAN("Aleman"), HINDU("Hindu"),
	ARABE("Arabe");
	
	@Getter
	private final String idioma;

	private Idioma(String idioma) {
		this.idioma = idioma;
	}
	
	
}
