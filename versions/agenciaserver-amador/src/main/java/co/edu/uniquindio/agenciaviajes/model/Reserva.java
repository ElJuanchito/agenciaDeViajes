/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaviajes.model;

import java.io.Serializable;
import java.time.LocalDate;

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
public class Reserva implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	public boolean clienteEstuvoAlli(Cliente cliente) {
		return this.cliente.equals(cliente) && estado == Estado.REALIZADA;
	}

	public double getPrecioFinal() {
		return cantPersonas * paquete.getPrecio().doubleValue();
	}

	public boolean clienteEstuvoGuia(Cliente cliente, GuiaTuristico guia) {
		return this.cliente.equals(cliente) && estado == Estado.REALIZADA && this.guia.equals(guia);
	}

}
