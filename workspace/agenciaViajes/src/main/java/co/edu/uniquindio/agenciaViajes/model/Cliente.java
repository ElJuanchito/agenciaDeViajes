/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author ElJuancho
 */
@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@Builder
public class Cliente extends Usuario {

	private String email;
	private String telefono;
	private String direccion;
	
	
}
