/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author ElJuancho
 */
@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cliente extends Usuario {

	@NonNull
	private String email;
	@NonNull
	private String telefono;
	@NonNull
	private String direccion;

	/**
	 * @param identificacion
	 * @param nombreCompleto
	 * @param email
	 * @param telefono
	 * @param direccion
	 * @author ElJuancho
	 */
	@Builder
	public Cliente(Long identificacion, String nombreCompleto, String email, String telefono, String direccion) {
		super(identificacion, nombreCompleto);
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
	}
}
