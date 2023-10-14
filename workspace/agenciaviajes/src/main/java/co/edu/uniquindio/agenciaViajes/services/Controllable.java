package co.edu.uniquindio.agenciaViajes.services;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public interface Controllable extends Initializable, Traducible, Clearable {

	@Override
	default void initialize(URL location, ResourceBundle resources) {
		inicializarVentana();
		Propiedades.getInstance().addListener(this);
	}

	public void inicializarVentana();

}
