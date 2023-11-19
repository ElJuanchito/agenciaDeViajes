package co.edu.uniquindio.agenciaviajes.controllers;

import co.edu.uniquindio.agenciaviajes.model.Administrador;
import co.edu.uniquindio.agenciaviajes.model.Cliente;
import co.edu.uniquindio.agenciaviajes.model.Loginable;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;

public class DataController {

	@Getter
	private SimpleObjectProperty<Loginable> loginActual;
	private static DataController instance;

	public static DataController getInstance() {
		if (instance == null)
			instance = new DataController();
		return instance;
	}

	private DataController() {
		loginActual = new SimpleObjectProperty<>();
	}

	public Loginable getLoginActualValue() {
		return loginActual.getValue();
	}

	public void selectUsuario(Loginable loginable) {
		loginActual.setValue(loginable);
	}

	public boolean usuarioEsCliente() {
		return loginActual.getValue() != null && loginActual.getValue() instanceof Cliente;
	}
	
	public boolean usuarioEsAdministrador() {
		return loginActual.getValue() != null && loginActual.getValue() instanceof Administrador;
	}
}
