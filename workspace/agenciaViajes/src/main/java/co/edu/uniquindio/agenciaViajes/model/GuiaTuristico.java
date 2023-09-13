/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "guias")
/**
 * 
 * @author ElJuancho
 */
public class GuiaTuristico extends Usuario {
	private Set<Idiomas> idomas;
	private Integer expHoras;
	
}
