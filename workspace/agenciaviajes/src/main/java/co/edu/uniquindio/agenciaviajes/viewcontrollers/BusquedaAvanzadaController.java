package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.controllers.PeticionController;
import co.edu.uniquindio.agenciaviajes.controllers.TipoPeticion;
import co.edu.uniquindio.agenciaviajes.controllers.Vista;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.exceptions.PeticionException;
import co.edu.uniquindio.agenciaviajes.model.BusquedaPaquetes;
import co.edu.uniquindio.agenciaviajes.model.Clima;
import co.edu.uniquindio.agenciaviajes.model.Paquete;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
import co.edu.uniquindio.agenciaviajes.utils.UtilsFX;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Pair;

public class BusquedaAvanzadaController implements DataControllable<Pair<List<Paquete>, String>> {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private CheckBox btnCiudadDestino, btnClima, btnFechaFInal, btnFechaInicio, btnNombreDestino, btnNombrePaquete,
			btnPrecioPaquete;

	@FXML
	private ComboBox<Clima> cbClima;

	@FXML
	private HBox contenedorPrecios;

	@FXML
	private DatePicker dpFechaFinal, dpFechaInicio;

	@FXML
	private Label lblDesde, lblHasta;

	@FXML
	private TextField txtCiudadDestino, txtNombreDestino, txtPrecioDesde, txtPrecioHasta, txtNombrePaquete;

	@FXML
	private BorderPane panelBusqueda;

	@FXML
	private Button btnFiltrar;

	@FXML
	void filtrarAction(ActionEvent event) {
		filtrarDatosAction();
	}

	@FXML
	void ciudadDestinoEvent(ActionEvent event) {
		ciudadDestinoAction();
	}

	@FXML
	void climaEvent(ActionEvent event) {
		climaAction();
	}

	@FXML
	void fechaFinalEvent(ActionEvent event) {
		fechaFinalAction();
	}

	@FXML
	void fechaInicioEvent(ActionEvent event) {
		fechaInicioAction();
	}

	@FXML
	void nombreDestinoEvent(ActionEvent event) {
		nombreDestinoAction();
	}

	@FXML
	void nombrePaqueteEvent(ActionEvent event) {
		nombrePaqueteAction();
	}

	@FXML
	void precioPaqueteEvent(ActionEvent event) {
		precioPaqueteAction();
	}

	private void ciudadDestinoAction() {

		if (btnCiudadDestino.isSelected()) {
			txtCiudadDestino.setPrefWidth(Region.USE_COMPUTED_SIZE);
			txtCiudadDestino.setPrefHeight(Region.USE_COMPUTED_SIZE);
		} else {
			txtCiudadDestino.setPrefWidth(0);
			txtCiudadDestino.setPrefHeight(0);
		}
	}

	private void climaAction() {
		if (btnClima.isSelected()) {
			cbClima.setPrefWidth(Region.USE_COMPUTED_SIZE);
			cbClima.setPrefHeight(Region.USE_COMPUTED_SIZE);
		} else {
			cbClima.setPrefWidth(0);
			cbClima.setPrefHeight(0);
		}

	}

	private void fechaFinalAction() {
		if (btnFechaFInal.isSelected()) {
			dpFechaFinal.setPrefWidth(Region.USE_COMPUTED_SIZE);
			dpFechaFinal.setPrefHeight(Region.USE_COMPUTED_SIZE);
		} else {
			dpFechaFinal.setPrefWidth(0);
			dpFechaFinal.setPrefHeight(0);
		}
	}

	private void fechaInicioAction() {
		if (btnFechaInicio.isSelected()) {
			dpFechaInicio.setPrefWidth(Region.USE_COMPUTED_SIZE);
			dpFechaInicio.setPrefHeight(Region.USE_COMPUTED_SIZE);
		} else {
			dpFechaInicio.setPrefWidth(0);
			dpFechaInicio.setPrefHeight(0);
		}
	}

	private void nombreDestinoAction() {
		if (btnNombreDestino.isSelected()) {
			txtNombreDestino.setPrefWidth(Region.USE_COMPUTED_SIZE);
			txtNombreDestino.setPrefHeight(Region.USE_COMPUTED_SIZE);
		} else {
			txtNombreDestino.setPrefWidth(0);
			txtNombreDestino.setPrefHeight(0);
		}
	}

	private void nombrePaqueteAction() {
		if (btnNombrePaquete.isSelected()) {
			txtNombrePaquete.setPrefWidth(Region.USE_COMPUTED_SIZE);
			txtNombrePaquete.setPrefHeight(Region.USE_COMPUTED_SIZE);
		} else {
			txtNombrePaquete.setPrefWidth(0);
			txtNombrePaquete.setPrefHeight(0);
		}
	}

	private void precioPaqueteAction() {
		if (btnPrecioPaquete.isSelected()) {
			txtPrecioDesde.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
			txtPrecioHasta.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
			lblDesde.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
			lblHasta.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		} else {
			txtPrecioDesde.setPrefSize(0, 0);
			txtPrecioHasta.setPrefSize(0, 0);
			lblDesde.setPrefSize(0, 0);
			lblHasta.setPrefSize(0, 0);
		}
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearData() {
		filtrarDatosAction();
	}

	private void filtrarDatosAction() {
		final String valorCiudadDestino = txtCiudadDestino.getText();
		final Clima valorClimaDestino = cbClima.getValue();
		final LocalDate valorFechaStart = dpFechaInicio.getValue();
		final LocalDate valorFechaFinal = dpFechaFinal.getValue();
		final String valorNombreDestino = txtNombreDestino.getText();
		final String valorNombrePaquete = txtNombrePaquete.getText();
		Double precioDesdePaquete = null;
		Double precioHastaPaquete = null;
		try {
			precioDesdePaquete = Double.parseDouble(txtPrecioDesde.getText());
			precioHastaPaquete = Double.parseDouble(txtPrecioHasta.getText());
		} catch (NumberFormatException e) {
		}
		BusquedaPaquetes busquedaPaquetes = BusquedaPaquetes.builder().ciudadDestino(valorCiudadDestino)
				.climaDestino(valorClimaDestino).fechaStart(valorFechaStart).fechaFinal(valorFechaFinal)
				.nombreDestino(valorNombreDestino).nombrePaquete(valorNombrePaquete)
				.precioDesdePaquete(precioDesdePaquete).precioHastaPaquete(precioHastaPaquete).build();

		PeticionController<BusquedaPaquetes, List<Paquete>> peticionController = new PeticionController<>(
				TipoPeticion.FILTRAR_PAQUETES, busquedaPaquetes);
		try {
			List<Paquete> paquetes = peticionController.realizarPeticion();
			if (paquetes == null || paquetes.isEmpty()) {
				panelBusqueda.setCenter(new Label("No hay paquetes")); // TODO property
				return;
			}
			Vista<List<Paquete>> vistaPaquetes = Vista.buildView("viewPaquetes");
			vistaPaquetes.cargarDato(paquetes);
			panelBusqueda.setCenter(vistaPaquetes.getParent());
		} catch (PeticionException | FXMLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void preInicializar() {
		UtilsFX.setAsNameTextField(txtNombreDestino);
		UtilsFX.setAsNameTextField(txtCiudadDestino);
		UtilsFX.setAsNameTextField(txtNombrePaquete);
		UtilsFX.setAsNumberTextfield(txtPrecioDesde);
		UtilsFX.setAsNumberTextfield(txtPrecioHasta);

		cbClima.setItems(FXCollections.observableArrayList(Clima.values()));
	}

	@Override
	public void inicializarDatos(Pair<List<Paquete>, String> dato) {
		Platform.runLater(() -> {
			btnNombrePaquete.setSelected(true);
			nombrePaqueteAction();
			txtNombrePaquete.setText(dato.getValue());
		});
		Vista<List<Paquete>> vistaPaquetes;
		try {
			vistaPaquetes = Vista.buildView("viewPaquetes");
			vistaPaquetes.cargarDato(dato.getKey());
			Platform.runLater(() -> panelBusqueda.setCenter(vistaPaquetes.getParent()));
		} catch (FXMLException e) {
			System.err.println(e.getMessage());
		}
	}
}
