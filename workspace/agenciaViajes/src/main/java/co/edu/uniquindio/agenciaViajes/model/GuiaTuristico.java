/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author ElJuancho
 */
@Entity
@Table(name = "guias")
public class GuiaTuristico extends Usuario {
	private Set<Idioma> idomas;
	private Integer expHoras;
}
