package co.edu.uniquindio.agenciaViajes.services;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaViajes.i18n.LanguageManager;
import co.edu.uniquindio.agenciaViajes.i18n.Traducible;
import javafx.fxml.Initializable;

public interface DataControllable<T> extends Initializable, Traducible, Clearable {

	/**
	 * Este metodo actualiza los datos dependiendo de lo que se necesite, no se
	 * llama de manera automatica entonces debe de llamarse por medio de la misma
	 * aplicacion
	 * 
	 * @param dato
	 */
	public void inicializarDatos(T dato);

	@Override
	default void initialize(URL location, ResourceBundle resources) {
		LanguageManager.getInstance().addListener(this);
	}
}
