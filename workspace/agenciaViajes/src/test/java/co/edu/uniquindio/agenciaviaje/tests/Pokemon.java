/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaviaje.tests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author ElJuancho
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Pokemon {
	private String nombre;
	private Tipo tipo;
	
	
}
