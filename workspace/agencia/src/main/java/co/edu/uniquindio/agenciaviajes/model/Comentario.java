package co.edu.uniquindio.agenciaviajes.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
public class Comentario {
	@NonNull
	private Cliente cliente;
	@NonNull
	private String comentario;
	@NonNull
	private Integer puntuacion;

}
