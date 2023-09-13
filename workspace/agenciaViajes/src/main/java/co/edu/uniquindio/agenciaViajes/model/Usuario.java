/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
public abstract class Usuario {
	
	@Id
	private Long identificacion;
	private String nombreCompleto;
}
