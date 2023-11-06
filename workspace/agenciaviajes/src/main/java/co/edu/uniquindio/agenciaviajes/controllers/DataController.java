package co.edu.uniquindio.agenciaviajes.controllers;

import co.edu.uniquindio.agenciaviajes.model.Loginable;
import javafx.beans.property.SimpleObjectProperty;

public class DataController {

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

	public void selectUsuario(Loginable loginable) {
		loginActual.setValue(loginable);
	}
}
