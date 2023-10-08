package co.edu.uniquindio.agenciaViajes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Clima {
	TROPICAL("Tropical"), SECO("Seco"), TEMPLADO("Templado"), FRIO("Frio"), CALUROSO("Caluroso"), POLAR("Polar");

	@Getter
	private final String clima;

	public static Clima getClimaByString(String climaString) {
		Clima[] values = values();
		for (Clima clima : values)
			if (clima.getClima().equals(climaString))
				return clima;
		return null;
	}

	public static String[] stringValues() {
		Clima[] values = values();
		String[] stringValues = new String[values.length];
		for (int i = 0; i < values.length; i++)
			stringValues[i] = values[i].getClima();
		return stringValues;
	}

}
