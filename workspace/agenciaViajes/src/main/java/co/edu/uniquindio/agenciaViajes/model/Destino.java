/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import co.edu.uniquindio.agenciaViajes.services.RecurArrayList;
import co.edu.uniquindio.agenciaViajes.services.RecurStrictList;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * 
 * @author ElJuancho
 */
@Entity
@Table(name = "destinos")
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Destino implements Comparable<Destino> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	@NonNull
	private String nombre;
	@NonNull
	private String ciudad;
	@NonNull
	private String descripcion;

	// Sujeto a revision
	private RecurArrayList<byte[]> imagenes;
	private Clima clima;

	@ManyToMany(mappedBy = "destinos")
	private RecurStrictList<Paquete> paquetes;

	/**
	 * @param nombre
	 * @param ciudad
	 * @param descripcion
	 * @param imagenes
	 * @param clima
	 * @author ElJuancho
	 */
	@Builder
	private Destino(String nombre, String ciudad, String descripcion, Clima clima) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.descripcion = descripcion;
		this.clima = clima;
		this.paquetes = new RecurStrictList<Paquete>();
	}

	@Override
	public String toString() {
		return "Destino [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", descripcion=" + descripcion
				+ ", imagenes=" + imagenes + ", clima=" + clima + "]";
	}

	@Override
	public int compareTo(Destino o) {
		return this.getId().compareTo(o.getId());
	}

}
