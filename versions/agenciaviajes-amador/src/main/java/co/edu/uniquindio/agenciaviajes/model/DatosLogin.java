package co.edu.uniquindio.agenciaviajes.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class DatosLogin implements Serializable, Loginable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private String id;
	@EqualsAndHashCode.Include
	private String password;

	@Override
	public String getContrasena() {
		return password;
	}

	@Override
	public String getUsuario() {
		return id;
	}
}
