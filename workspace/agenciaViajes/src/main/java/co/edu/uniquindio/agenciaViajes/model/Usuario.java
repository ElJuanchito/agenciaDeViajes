/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * 
 * @author ElJuancho
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public abstract class Usuario {

	@Id
	@EqualsAndHashCode.Include
	protected Long identificacion;
	@NonNull
	protected String nombreCompleto;

}
