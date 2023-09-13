/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author ElJuancho
 */
@MappedSuperclass
@Getter @Setter
public abstract class Usuario {
	
	@Id
	private Long identificacion;
	private String nombreCompleto;
	
}
