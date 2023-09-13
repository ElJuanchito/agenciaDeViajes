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
public enum Estado {
	PENDIENTE("Pendiente"), CONFIRMADA("Confirmada"), CANCELADA("Cancelada");
	
	@Getter
	private final String estado;
}
