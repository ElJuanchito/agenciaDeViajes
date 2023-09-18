/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
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
@Table(name = "guias")
@Getter
@Setter
@NoArgsConstructor
public class GuiaTuristico extends Usuario {
	@NonNull
	@ElementCollection(targetClass = Idioma.class)
	private List<Idioma> idiomas = new ArrayList<Idioma>();
	@NonNull
	private Integer expHoras;

	/**
	 * @param identificacion
	 * @param nombreCompleto
	 * @param expHoras
	 * @author ElJuancho
	 */
	@Builder
	public GuiaTuristico(Long identificacion, String nombreCompleto, Integer expHoras, List<Idioma> idiomas) {
		super(identificacion, nombreCompleto);
		this.expHoras = expHoras;
		this.idiomas = idiomas;
	}

	@Override
	public String toString() {
		return "GuiaTuristico [nombreCompleto=" + nombreCompleto + ", idiomas=" + idiomas + ", expHoras=" + expHoras
				+ ", identificacion=" + identificacion + "]";
	}

}
