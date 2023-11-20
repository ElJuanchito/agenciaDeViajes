package co.edu.uniquindio.agenciaviajes.model;

import java.io.Serializable;

public interface Loginable extends Serializable {

	public String getContrasena();

	public String getUsuario();
}
