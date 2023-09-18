/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
public class Destino {
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
	transient private List<byte[]> imagenes;
	private Clima clima;

	@ManyToMany(mappedBy = "destinos")
	private List<Paquete> paquetes;

	/**
	 * @param nombre
	 * @param ciudad
	 * @param descripcion
	 * @param imagenes
	 * @param clima
	 * @author ElJuancho
	 */
	@Builder
	public Destino(String nombre, String ciudad, String descripcion, Clima clima) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.descripcion = descripcion;
		this.clima = clima;
		this.paquetes = new ArrayList<Paquete>();
	}

	@Override
	public String toString() {
		return "Destino [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", descripcion=" + descripcion
				+ ", imagenes=" + imagenes + ", clima=" + clima + "]";
	}

}
