package co.edu.uniquindio.agenciaviajes.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name="Administradores")
@Getter
@Setter
@NoArgsConstructor
public class Administrador extends Usuario implements Loginable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NonNull
	private String contrasena;

	@OneToOne
	private Imagen imagen;

	@Builder
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
