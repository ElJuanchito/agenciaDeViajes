package co.edu.uniquindio.agenciaViajes.services;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaViajes.i18n.LanguageManager;
import co.edu.uniquindio.agenciaViajes.i18n.Traducible;
import javafx.fxml.Initializable;

public interface Controllable extends Initializable, Traducible, Clearable {

	@Override
	default void initialize(URL location, ResourceBundle resources) {
		inicializarVentana();
		LanguageManager.getInstance().addListener(this);
	}

	public void inicializarVentana();

}
