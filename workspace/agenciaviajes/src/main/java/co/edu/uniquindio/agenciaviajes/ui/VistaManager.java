package co.edu.uniquindio.agenciaviajes.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.uniquindio.agenciaviajes.application.App;
import co.edu.uniquindio.agenciaviajes.controllers.MainMenuController;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.exceptions.MovimientoIndefinidoException;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Getter;

public class VistaManager {
	private static VistaManager instance;

	private List<InfoVista> historialVistasCliente;

	@Getter
	private SimpleBooleanProperty obsSiguienteCliente;
	@Getter
	private SimpleBooleanProperty obsAnteriorCliente;

	private <T> InfoVista obtenerPar(OrgVista orgVista, TipoVista tipoVista, T valor) {
		return new InfoVista(orgVista, tipoVista, valor);
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

		indiceCliente.addListener((observable, oldValue, newValue) -> {
			obsAnteriorCliente.setValue(newValue.intValue() > 0);
			obsSiguienteCliente.setValue(newValue.intValue() < historialVistasCliente.size() - 1);
		});
	}

	private Map<TipoVista, Vista<? extends Object>> mapaVistas;
	private InfoVista vistaActualCliente;
	private SimpleIntegerProperty indiceCliente;

	public <T> void cambiarVista(TipoVista tipo, T dato) throws FXMLException {
		cambiarVista(tipo, dato, true);
	}

	private <T> void cambiarVista(TipoVista tipo, T dato, boolean eliminarSiguientes) throws FXMLException {
		Vista<T> vista = cargarVista(tipo);
		if (eliminarSiguientes && tipo != TipoVista.MENU_PRINCIPAL) {
			limpiarVistasSiguientesCliente();
			historialVistasCliente.add(obtenerPar(OrgVista.TODO, tipo, dato));
			indiceCliente.setValue(historialVistasCliente.size() - 1);
		}
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

	private <T> void cambiarVistaCliente(TipoVista tipo, T dato, boolean eliminarSiguientes) throws FXMLException {
		InfoVista infoVista = obtenerPar(OrgVista.CLIENTE, tipo, dato);
		System.out.println("vistaActualCliente: " + vistaActualCliente);
		System.out.println("infoVista: " + infoVista);
		if (vistaActualCliente != null && vistaActualCliente.equals(infoVista))
			return;
		vistaActualCliente = infoVista;
		Vista<T> vista = cargarVista(tipo);
		if (eliminarSiguientes) {
			limpiarVistasSiguientesCliente();
			historialVistasCliente.add(obtenerPar(OrgVista.CLIENTE, tipo, dato));
			indiceCliente.setValue(historialVistasCliente.size() - 1);
		}
		vista.cargarDato(dato);
		Platform.runLater(() -> {
			MainMenuController.getInstance().setContent(vista.getParent());
		});
	}

	public <T> void cambiarVistaCliente(TipoVista tipo, T dato) throws FXMLException {
		cambiarVistaCliente(tipo, dato, true);
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
		cargarInfoHistorial();
	}

	public void siguienteCliente() throws FXMLException, MovimientoIndefinidoException {
		indiceCliente.setValue(indiceCliente.getValue() + 1);
		if (indiceCliente.greaterThanOrEqualTo(historialVistasCliente.size()).getValue()) {
			indiceCliente.setValue(historialVistasCliente.size() - 1);
			throw new MovimientoIndefinidoException("No hay vista siguiente a esta");
		}
		cargarInfoHistorial();
	}

	private void cargarInfoHistorial() throws FXMLException {
		InfoVista tripleDato = historialVistasCliente.get(indiceCliente.getValue());
		switch (tripleDato.orgVista) {
		case TODO -> cambiarVista(tripleDato.tipoVista, tripleDato.dato, false);
		case CLIENTE -> cambiarVistaCliente(tripleDato.tipoVista, tripleDato.dato, false);
		default -> throw new RuntimeException();
		}
	}

}
