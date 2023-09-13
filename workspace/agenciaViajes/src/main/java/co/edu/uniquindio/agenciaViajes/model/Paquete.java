/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
@Table(name = "paquetes")
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Paquete implements Comparable<Paquete>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	@NonNull
	private String nombre;
	@NonNull
	private Integer duracionDias;
	@NonNull
	private String serviciosAdicionales;
	@NonNull
	private BigDecimal precio;
	@NonNull
	private Integer cupoMaximo;
	@NonNull
	private LocalDateTime fechaIncio;
	@NonNull
	private LocalDateTime fechaFin;

	@ManyToMany
	@JoinTable(name = "paquete_destino", joinColumns = @JoinColumn(name = "paquete_id"), inverseJoinColumns = @JoinColumn(name = "destino_id"))
	private RecurStrictList<Destino> destinos;

	/**
	 * @param nombre
	 * @param duracionDias
	 * @param serviciosAdicionales
	 * @param precio
	 * @param cupoMaximo
	 * @param fechaIncio
	 * @param fechaFin
	 * @author ElJuancho
	 */
	@Builder
	public Paquete(String nombre, Integer duracionDias, String serviciosAdicionales, BigDecimal precio,
			Integer cupoMaximo, LocalDateTime fechaIncio, LocalDateTime fechaFin) {
		super();
		this.nombre = nombre;
		this.duracionDias = duracionDias;
		this.serviciosAdicionales = serviciosAdicionales;
		this.precio = precio;
		this.cupoMaximo = cupoMaximo;
		this.fechaIncio = fechaIncio;
		this.fechaFin = fechaFin;
		this.destinos = new RecurStrictList<Destino>();
	}

	@Override
	public String toString() {
		return "Paquete [id=" + id + ", nombre=" + nombre + ", duracionDias=" + duracionDias + ", serviciosAdicionales="
				+ serviciosAdicionales + ", precio=" + precio + ", cupoMaximo=" + cupoMaximo + ", fechaIncio="
				+ fechaIncio + ", fechaFin=" + fechaFin + "]";
	}

	@Override
	public int compareTo(Paquete o) {
		return this.getId().compareTo(o.getId());
	}
	
	

}
