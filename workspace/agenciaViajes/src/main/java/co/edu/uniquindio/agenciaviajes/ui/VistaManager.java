package co.edu.uniquindio.agenciaviajes.ui;

import java.util.HashMap;
import java.util.Map;

import co.edu.uniquindio.agenciaviajes.application.App;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.i18n.LanguageManager;

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

	@SuppressWarnings("unchecked")
	public <T> void cambiarVista(TipoVista tipo, T dato) throws FXMLException {
		Vista<T> vista = (Vista<T>) mapaVistas.get(tipo);
		if (vista == null) {
			vista = Vista.buildView(tipo.getRuta());
			mapaVistas.put(tipo, vista);
		}
		vista.clearData();
		vista.updateLanguage(LanguageManager.getInstance().getBundle());
		vista.inicializarDatos(dato);
		App.setRoot(vista.getParent());
	}
}
