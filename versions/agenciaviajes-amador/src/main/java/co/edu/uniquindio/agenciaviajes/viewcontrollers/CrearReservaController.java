package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.controllers.CrearReservaLogicController;
import co.edu.uniquindio.agenciaviajes.model.GuiaTuristico;
import co.edu.uniquindio.agenciaviajes.model.Idioma;
import co.edu.uniquindio.agenciaviajes.model.Paquete;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
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

		CrearReservaLogicController instance = CrearReservaLogicController.getInstance();

		clmId.setCellValueFactory(instance.crearColID());
		clmName.setCellValueFactory(instance.crearColNombre());
		clmExp.setCellValueFactory(instance.crearColExperiencia());

		// Actualizacion de tablas por cada vez que se seleccione un guia
		tblGuias.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			btnLimpiarSeleccion.setDisable(newValue == null);
			if (newValue != null) {
				tblIdiomas.setItems(FXCollections.observableArrayList(newValue.getIdiomas()));
			}
		});

		// Configurar el Spinner con el SpinnerValueFactory personalizado
		spnCant.setValueFactory(instance.crearSpinnerValueFactory(Integer.MAX_VALUE));

	}

	private void limpiarSeleccionAction() {
		tblGuias.getSelectionModel().clearSelection();
		tblIdiomas.setItems(FXCollections.observableArrayList());
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		lblTitle.setText(bundle.getString("CrearReservaController.lblTitle"));
		lblinfoPackage.setText(bundle.getString("CrearReservaController.lblinfoPackage"));
		lblRangoDisponible.setText(bundle.getString("CrearReservaController.lblRangoDisponible"));
		lblFechaInicial.setText(bundle.getString("CrearReservaController.lblFechaInicial"));
		lblFechaFinal.setText(bundle.getString("CrearReservaController.lblFechaFinal"));
		lblCant.setText(bundle.getString("CrearReservaController.lblCant"));
		lblCupoMaximo.setText(bundle.getString("CrearReservaController.lblCupoMaximo"));
		lblGuia.setText(bundle.getString("CrearReservaController.lblGuia"));
		clmIdioma.setText(bundle.getString("CrearReservaController.clmIdioma"));
		clmId.setText(bundle.getString("CrearReservaController.clmId"));
		clmName.setText(bundle.getString("CrearReservaController.clmName"));
		clmExp.setText(bundle.getString("CrearReservaController.clmExp"));
		btnLimpiarSeleccion.setText(bundle.getString("CrearReservaController.btnLimpiarSeleccion"));
		btnReservar.setText(bundle.getString("CrearReservaController.btnReservar"));
	}

	@Override
	public void clearData() {
		dtpFecha.getEditor().setText("");
		spnCant.getEditor().setText("0");
		limpiarSeleccionAction();
	}

	@Override
	public void inicializarDatos(Paquete paquete) {
		CrearReservaLogicController instance = CrearReservaLogicController.getInstance();
		this.paquete = paquete;
		lblNamePackage.setText(paquete.getNombre());

		lblInfoFechaInicial.setText(instance.getFechaFormato(paquete.getFechaIncio()));
		lblInfoFechaFinal.setText(instance.getFechaFormato(paquete.getFechaFin()));
		lblInfoCupoMaximo.setText(String.valueOf(paquete.getCupoMaximo()));
		spnCant.setValueFactory(instance.crearSpinnerValueFactory(paquete.getCupoMaximo()));
	}

	private void reservarAction() {
		CrearReservaLogicController.getInstance().crearReserva(spnCant.getValue(), paquete,
				tblGuias.getSelectionModel().getSelectedItem());
	}

}