package co.edu.uniquindio.agenciaviajes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Preferencia {
	@EqualsAndHashCode.Include
	private Long idDestino;
	@NonNull
	private Clima clima;
	@NonNull
	private TipoDestino tipoDestino;
}
