/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Builder;
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
public class Paquete {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
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
	private List<Destino> destinos;

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
		this.destinos = new ArrayList<Destino>();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paquete other = (Paquete) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return "Paquete [id=" + id + ", nombre=" + nombre + ", duracionDias=" + duracionDias + ", serviciosAdicionales="
				+ serviciosAdicionales + ", precio=" + precio + ", cupoMaximo=" + cupoMaximo + ", fechaIncio="
				+ fechaIncio + ", fechaFin=" + fechaFin + "]";
	}


}
