package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.controllers.DataController;
import co.edu.uniquindio.agenciaviajes.controllers.PeticionController;
import co.edu.uniquindio.agenciaviajes.controllers.TipoPeticion;
import co.edu.uniquindio.agenciaviajes.controllers.VistaManager;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.exceptions.PeticionException;
import co.edu.uniquindio.agenciaviajes.model.Cliente;
import co.edu.uniquindio.agenciaviajes.model.GuiaTuristico;
import co.edu.uniquindio.agenciaviajes.model.Idioma;
import co.edu.uniquindio.agenciaviajes.model.Paquete;
import co.edu.uniquindio.agenciaviajes.model.Reserva;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CrearReservaController implements DataControllable<Paquete> {

	@FXML
	private Button btnReservar, btnLimpiarSeleccion;

	@FXML
	private TableColumn<GuiaTuristico, String> clmExp, clmId, clmIdioma, clmName;

	@FXML
	private DatePicker dtpFecha;

	@FXML
	private Label lblCant, lblFecha, lblGuia, lblNamePackage, lblTitle, lblinfoPackage, lblCupoMaximo, lblFechaFinal,
			lblFechaInicial, lblInfoCupoMaximo, lblInfoFechaFinal, lblInfoFechaInicial, lblRangoDisponible;

	@FXML
	private Spinner<Integer> spnCant;

	@FXML
	private TableView<GuiaTuristico> tblGuias;

	@FXML
	private TableView<Idioma> tblIdiomas;

	private DateTimeFormatter dateTimePattern;

	private Paquete paquete;

	@FXML
	void reservarEvent(ActionEvent event) {
		reservarAction();
	}

	@FXML
	void limpiarSeleccionEvent(ActionEvent event) {
		limpiarSeleccionAction();
	}

	@Override
	public void preInicializar() {

		dateTimePattern = DateTimeFormatter.ofPattern("dd/MM/yyyy h:mm a");

		clmId.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getIdentificacion()));
		clmName.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getNombreCompleto()));

		DecimalFormat format = new DecimalFormat("#");

		clmExp.setCellValueFactory(cell -> new ReadOnlyStringWrapper(format.format(cell.getValue().getExpHoras())));

		// Actualizacion de tablas por cada vez que se seleccione un guia
		tblGuias.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			btnLimpiarSeleccion.setDisable(newValue == null);
			if (newValue != null) {
				tblIdiomas.setItems(FXCollections.observableArrayList(newValue.getIdiomas()));
			}
		});

		// Configurar el Spinner con el SpinnerValueFactory personalizado
		spnCant.setValueFactory(crearSpinnerValueFactory());

	}

	private IntegerSpinnerValueFactory crearSpinnerValueFactory() {
		return new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0) {
			@Override
			public void decrement(int steps) {
				int currentValue = this.getValue();
				int newValue = currentValue - steps;
				this.setValue(newValue >= 0 ? newValue : 0);
			}

			@Override
			public void increment(int steps) {
				this.setValue(this.getValue() + steps);
			}
		};
	}

	private void limpiarSeleccionAction() {
		tblGuias.getSelectionModel().clearSelection();
		tblIdiomas.setItems(FXCollections.observableArrayList());
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearData() {
		dtpFecha.getEditor().setText("");
		spnCant.getEditor().setText("0");
		limpiarSeleccionAction();
	}

	@Override
	public void inicializarDatos(Paquete paquete) {
		this.paquete = paquete;
		lblNamePackage.setText(paquete.getNombre());
		lblInfoFechaInicial.setText(paquete.getFechaIncio().format(dateTimePattern));
		lblInfoFechaFinal.setText(paquete.getFechaFin().format(dateTimePattern));
		lblInfoCupoMaximo.setText(String.valueOf(paquete.getCupoMaximo()));
	}

	private void reservarAction() {

		Reserva reserva = Reserva.builder().cliente((Cliente) DataController.getInstance().getLoginActual())
				.cantPersonas(spnCant.getValue()).paquete(paquete)
				.guiaTuristico(tblGuias.getSelectionModel().getSelectedItem()).build();
		try {
			Reserva reservaNueva = new PeticionController<Reserva, Reserva>(TipoPeticion.AGREGAR, reserva)
					.realizarPeticion();
			MainPaneController.getInstance()
					.showAlertAccept("Se ha creado una reserva, ¿quieres recibir un comprobante?", () -> {
						Platform.runLater(() -> crearPdfReserva(reservaNueva));
					});
		} catch (PeticionException e) {
			MainPaneController.getInstance().showAlert(e.getMessage());
		}

	}

	private void crearPdfReserva(final Reserva reservaNueva) {
		try {
			VistaManager.getInstance().crearPDFReserva(reservaNueva);
		} catch (FXMLException e) {
			throw new RuntimeException(e);
		}
	}

}