package co.edu.uniquindio.agenciaviajes.model;

import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Administrador extends Usuario implements Loginable {

	@NonNull
	private String contrasena;

	@OneToOne
	private Imagen imagen;

	public Administrador(@NonNull String identificacion, @NonNull String nombreCompleto, @NonNull String contrasena,
			Imagen imagen) {
		super(identificacion, nombreCompleto);
		this.contrasena = contrasena;
		this.imagen = imagen;
	}

	@Override
	public String getUsuario() {
		return getIdentificacion();
	}
}
