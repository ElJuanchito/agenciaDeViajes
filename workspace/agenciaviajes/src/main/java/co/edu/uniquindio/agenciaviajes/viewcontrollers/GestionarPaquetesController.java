package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.model.Paquete;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionarPaquetesController implements Controllable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblTitle;

	@FXML
	private TextField txtBuscar;

	@FXML
	private TableView<Paquete> tablePaquetes;

	@FXML
	private TableColumn<Paquete, String> colId, colNombre, colDuracion, colServicios, colCupoMaximo, colFechaInicio,
			colFechaFin, colPrecio;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnAgregar;

	@FXML
	void agregarEvent(ActionEvent event) {

	}

	@FXML
	void eliminarEvent(ActionEvent event) {

	}

	@Override
	public void preInicializar() {
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		lblTitle.setText(bundle.getString("GestionarPaquetesController.lblTitle"));
		txtBuscar.setPromptText(bundle.getString("GestionarPaquetesController.txtBuscar"));
		colId.setText(bundle.getString("GestionarPaquetesController.colId"));
		colNombre.setText(bundle.getString("GestionarPaquetesController.colNombre"));
		colDuracion.setText(bundle.getString("GestionarPaquetesController.colDuracion"));
		colServicios.setText(bundle.getString("GestionarPaquetesController.colServicios"));
		colCupoMaximo.setText(bundle.getString("GestionarPaquetesController.colCupoMaximo"));
		colFechaInicio.setText(bundle.getString("GestionarPaquetesController.colFechaInicio"));
		colFechaFin.setText(bundle.getString("GestionarPaquetesController.colFechaFin"));
	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub

	}
}
