/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaviajes.model;

import java.io.Serializable;
import java.time.LocalDate;

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
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor

@ToString
public class Reserva implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	private Long id;
	private LocalDate fechaSolicitud;
	private Cliente cliente;
	private Integer cantPersonas;
	private Paquete paquete;

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
	
	public boolean clienteEstuvoGuia(Cliente cliente, GuiaTuristico guia) {
		return this.cliente.equals(cliente) && estado==Estado.REALIZADA && this.guia.equals(guia);
	}

}
