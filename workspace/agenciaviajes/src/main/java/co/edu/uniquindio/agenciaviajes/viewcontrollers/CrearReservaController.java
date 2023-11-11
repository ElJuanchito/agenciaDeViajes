package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.model.GuiaTuristico;
import co.edu.uniquindio.agenciaviajes.model.Idioma;
import co.edu.uniquindio.agenciaviajes.model.Paquete;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
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
				if (newValue >= 0) {
					this.setValue(newValue);
				} else {
					this.setValue(0);
				}
			}

			@Override
			public void increment(int steps) {
				int currentValue = this.getValue();
				this.setValue(currentValue + steps);
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
		lblNamePackage.setText(paquete.getNombre());
		lblInfoFechaInicial.setText(paquete.getFechaIncio().format(dateTimePattern));
		lblInfoFechaFinal.setText(paquete.getFechaFin().format(dateTimePattern));
		lblInfoCupoMaximo.setText(String.valueOf(paquete.getCupoMaximo()));
	}

	private void reservarAction() {
		MainPaneController.getInstance().showAlert("Not yet");
	}

}