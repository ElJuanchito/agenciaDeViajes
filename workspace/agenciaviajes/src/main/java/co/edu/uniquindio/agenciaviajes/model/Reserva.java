/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaviajes.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
@Table(name = "reservas")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor

@ToString
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private LocalDate fechaSolicitud;
	@ManyToOne
	private Cliente cliente;
	private Integer cantPersonas;
	@OneToOne
	private Paquete paquete;

	@ManyToOne
	private GuiaTuristico guia;
	private Estado estado;

	@Builder
	public Reserva(Cliente cliente, Integer cantPersonas, Paquete paquete, GuiaTuristico guiaTuristico, Estado estado) {
		this.fechaSolicitud = LocalDate.now();
		this.cantPersonas = cantPersonas;
		this.cliente = cliente;
		this.paquete = paquete;
		this.guia = guiaTuristico;
		this.estado = estado;
	}

}
