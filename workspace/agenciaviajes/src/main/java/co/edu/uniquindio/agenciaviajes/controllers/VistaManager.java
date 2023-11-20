package co.edu.uniquindio.agenciaviajes.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.uniquindio.agenciaviajes.application.App;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.exceptions.MovimientoIndefinidoException;
import co.edu.uniquindio.agenciaviajes.viewcontrollers.MainMenuController;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;

public class VistaManager {
	private static VistaManager instance;

	private List<InfoVista> historialVistasCliente;
	@Getter
	private SimpleBooleanProperty obsSiguienteCliente;
	@Getter
	private SimpleBooleanProperty obsAnteriorCliente;
	private Map<TipoVista, Vista<? extends Object>> mapaVistas;
	@Getter
	private SimpleObjectProperty<InfoVista> vistaActualCliente;
	private SimpleIntegerProperty indiceCliente;

	private <T> InfoVista obtenerInfoVista(TipoVista tipoVista, T valor) {
		return new InfoVista(tipoVista, valor);
	}

	public static VistaManager getInstance() {
		if (instance == null)
			instance = new VistaManager();
		return instance;
	}

	private VistaManager() {
		mapaVistas = new HashMap<TipoVista, Vista<? extends Object>>();
		historialVistasCliente = new ArrayList<>();
		indiceCliente = new SimpleIntegerProperty();
		obsSiguienteCliente = new SimpleBooleanProperty(false);
		obsAnteriorCliente = new SimpleBooleanProperty(false);
		vistaActualCliente = new SimpleObjectProperty<InfoVista>();

		indiceCliente.addListener((observable, oldValue, newValue) -> {
			obsAnteriorCliente.setValue(newValue.intValue() > 0);
			obsSiguienteCliente.setValue(newValue.intValue() < historialVistasCliente.size() - 1);
		});
	}

	public <T> void cambiarVista(TipoVista tipo, T dato) throws FXMLException {
		Vista<T> vista = cargarVista(tipo);
		Vista<T> vistaFinal = vista;
		Platform.runLater(() -> {
			vistaFinal.limpiarDatos();
			vistaFinal.cargarIdioma();
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

	private <T> void cambiarVistaCliente(TipoVista tipo, T dato, boolean contarHistorial, boolean limpiarCampos)
			throws FXMLException {
		InfoVista infoVista = obtenerInfoVista(tipo, dato);
		if (contarHistorial && !limpiarCampos && (vistaActualCliente.getValue() == infoVista
				|| (vistaActualCliente.getValue() != null && vistaActualCliente.getValue().equals(infoVista))))
			return;
		vistaActualCliente.setValue(infoVista);
		Vista<T> vista = cargarVista(tipo);
		if (contarHistorial) {
			limpiarVistasSiguientesCliente();
			historialVistasCliente.add(obtenerInfoVista(tipo, dato));
			indiceCliente.setValue(historialVistasCliente.size() - 1);
		}
		if (limpiarCampos)
			vista.limpiarDatos();
		vista.cargarDato(dato);
		Platform.runLater(() -> {
			MainMenuController.getInstance().setContent(vista.getParent());
		});
	}

	public <T> void cambiarVistaCliente(TipoVista tipo, T dato) throws FXMLException {
		cambiarVistaCliente(tipo, dato, true, true);
	}

	private void limpiarVistasSiguientesCliente() {
		Integer value = indiceCliente.getValue();
		while (value < historialVistasCliente.size() - 1)
			historialVistasCliente.remove(value + 1);
	}

	public void volverCliente() throws FXMLException, MovimientoIndefinidoException {
		indiceCliente.setValue(indiceCliente.getValue() - 1);
		if (indiceCliente.lessThan(0).getValue()) {
			indiceCliente.setValue(0);
			throw new MovimientoIndefinidoException("No hay vista anterior a esta");
		}
		cargarInfoHistorialCliente();
	}

	public void reloadCliente() throws FXMLException {
		InfoVista tripleDato = historialVistasCliente.get(indiceCliente.getValue());
		cambiarVistaCliente(tripleDato.tipoVista, tripleDato.dato, false, true);
	}

	public void siguienteCliente() throws FXMLException, MovimientoIndefinidoException {
		indiceCliente.setValue(indiceCliente.getValue() + 1);
		if (indiceCliente.greaterThanOrEqualTo(historialVistasCliente.size()).getValue()) {
			indiceCliente.setValue(historialVistasCliente.size() - 1);
			throw new MovimientoIndefinidoException("No hay vista siguiente a esta");
		}
		cargarInfoHistorialCliente();
	}

	private void cargarInfoHistorialCliente() throws FXMLException {
		InfoVista tripleDato = historialVistasCliente.get(indiceCliente.getValue());
		cambiarVistaCliente(tripleDato.tipoVista, tripleDato.dato, false, false);
	}

}
