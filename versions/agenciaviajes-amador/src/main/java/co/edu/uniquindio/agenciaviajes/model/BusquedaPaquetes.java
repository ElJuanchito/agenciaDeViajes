package co.edu.uniquindio.agenciaviajes.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.function.Predicate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BusquedaPaquetes implements Serializable {

	private Clima climaDestino;

	private LocalDate fechaStart, fechaFinal;

	private String ciudadDestino, nombreDestino;

	private String nombrePaquete;

	private Double precioDesdePaquete, precioHastaPaquete;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Predicate<Paquete> getPredicate() {
		return paquete -> {
			boolean flagCiudadDes = ciudadDestino.isEmpty() ? true : paquete.contieneCiudad(ciudadDestino);
			boolean flagClima = climaDestino == null ? true : paquete.contieneClima(climaDestino);
			boolean flagFechas = fechaStart == null || fechaFinal == null ? true
					: paquete.estaEntreFechas(fechaStart, fechaFinal);
			boolean flagNombreDes = nombreDestino == null ? true : paquete.contieneNombreDestino(nombreDestino);
			boolean flagNombrePaquete = nombrePaquete == null ? true : paquete.tieneNombre(nombrePaquete);
			boolean flagPrecio = precioDesdePaquete == null || precioHastaPaquete == null ? true
					: paquete.tienePrecioEntre(precioDesdePaquete, precioHastaPaquete);
			return flagCiudadDes && flagClima && flagFechas && flagNombreDes && flagNombrePaquete && flagPrecio;
		};
	}

}
