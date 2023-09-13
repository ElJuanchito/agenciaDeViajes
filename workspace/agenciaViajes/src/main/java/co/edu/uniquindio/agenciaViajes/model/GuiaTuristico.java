/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import co.edu.uniquindio.agenciaViajes.services.RecurStrictList;
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
@Table(name = "guias")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class GuiaTuristico extends Usuario {
	@NonNull
	private RecurStrictList<Idioma> idomas = new RecurStrictList<Idioma>();
	@NonNull
	private Integer expHoras;

	/**
	 * @param identificacion
	 * @param nombreCompleto
	 * @param expHoras
	 * @author ElJuancho
	 */
	@Builder
	public GuiaTuristico(Long identificacion, String nombreCompleto, Integer expHoras) {
		super(identificacion, nombreCompleto);
		this.expHoras = expHoras;
	}
	
	

}
