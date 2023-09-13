/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "paquetes")
/**
 * 
 * @author ElJuancho
 */
public class Paquete {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private Integer duracionDias;
	private String serviciosAdicionales;
	private BigDecimal precio;
	private Integer cupoMaximo;
	private LocalDateTime fechaIncio;
	private LocalDateTime fechaFin;

	@ManyToMany
	@JoinTable(name = "paquete_destino", joinColumns = @JoinColumn(name = "paquete_id"), inverseJoinColumns = @JoinColumn(name = "destino_id"))
	private Set<Destino> destinos = new HashSet<>();

	/**
	 * 
	 * @author ElJuancho
	 */
	protected Paquete() {
		// TODO Auto-generated constructor stub
	}

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
		this.destinos = new HashSet<Destino>();
	}

}
