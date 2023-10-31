package co.edu.uniquindio.agenciaviajes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Comentario {
	@NonNull
	private Cliente cliente;
	@NonNull
	private String comentario;
	@NonNull
	private Integer puntuacion;

}
