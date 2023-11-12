package co.edu.uniquindio.agenciaserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Estado {
	PENDIENTE("Pendiente"), CONFIRMADA("Confirmada"), CANCELADA("Cancelada"), REALIZADA("Realizada");

	@Getter
	private String estado;

	public static String[] stringValues() {
		Estado[] values = values();
		String[] stringValues = new String[values.length];
		for (int i = 0; i < values.length; i++)
			stringValues[i] = values[i].getEstado();
		return stringValues;
	}

	public static Estado getEstadoByString(String estadoString) {
		Estado[] values = values();
		for (Estado estado : values)
			if (estado.getEstado().equals(estadoString))
				return estado;
		return null;
	}
}
