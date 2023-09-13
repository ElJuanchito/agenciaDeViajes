/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @author ElJuancho
 */
@AllArgsConstructor
public enum Clima {
	TROPICAL("Tropical"), SECO("Seco"),  TEMPLADO("Templado"), FRIO("Frio"), CALUROSO("Caluroso"), POLAR("Polar");

	@Getter
	private final String clima;
}
