package co.edu.uniquindio.agenciaviajes.ui;

import java.util.HashMap;
import java.util.Map;

import co.edu.uniquindio.agenciaviajes.application.App;
import co.edu.uniquindio.agenciaviajes.controllers.MainMenuController;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import javafx.application.Platform;

public class VistaManager {
	private static VistaManager instance;

	public static VistaManager getInstance() {
		if (instance == null)
			instance = new VistaManager();
		return instance;
	}

	private VistaManager() {
		mapaVistas = new HashMap<TipoVista, Vista<? extends Object>>();
	}

	private Map<TipoVista, Vista<? extends Object>> mapaVistas;

	public <T> void cambiarVista(TipoVista tipo, T dato) throws FXMLException {
		Vista<T> vista = cargarVista(tipo);
		Vista<T> vistaFinal = vista;
		Platform.runLater(() -> {
			vistaFinal.cargarDato(dato);
			App.setRoot(vistaFinal.getParent());
		});
	}

	@SuppressWarnings("unchecked")
	private <T> Vista<T> cargarVista(TipoVista tipo) throws FXMLException {
		Vista<T> vista = (Vista<T>) mapaVistas.get(tipo);
		if (vista == null) {
			vista = Vista.buildView(tipo.getRuta());
			mapaVistas.put(tipo, vista);
		}
		return vista;
	}

	public <T> void cambiarVistaCliente(TipoVista tipo, T dato) throws FXMLException {
		Vista<T> vista = cargarVista(tipo);
		vista.cargarDato(dato);
		Platform.runLater(() -> {
			MainMenuController.getInstance().setContent(vista.getParent());
		});
	}
}
