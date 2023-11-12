package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.model.GuiaTuristico;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionarGuiasController implements Controllable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblTitle;

	@FXML
	private TextField txtBuscar;

	@FXML
	private TableView<GuiaTuristico> tableClientes;

	@FXML
	private TableColumn<GuiaTuristico, String> colNombre, colIdentificacion, colHorasExperiencia, colIdiomas;

	@FXML
	private Button btnEliminar, btnSolicitudes;

	@FXML
	void solicitudesEvent(ActionEvent event) {

	}

	@FXML
	void eliminarEvent(ActionEvent event) {

	}

	@Override
	public void preInicializar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		lblTitle.setText(bundle.getString("GestionarGuiasController.lblTitle"));
		txtBuscar.setPromptText(bundle.getString("GestionarGuiasController.txtBuscar"));
		colNombre.setText(bundle.getString("GestionarGuiasController.colNombre"));
		colIdentificacion.setText(bundle.getString("GestionarGuiasController.colIdentificacion"));
		colHorasExperiencia.setText(bundle.getString("GestionarGuiasController.colHorasExperiencia"));
		colIdiomas.setText(bundle.getString("GestionarGuiasController.colIdiomas"));
		btnEliminar.setText(bundle.getString("GestionarGuiasController.btnEliminar"));
		btnSolicitudes.setText(bundle.getString("GestionarGuiasController.btnSolicitudes"));
	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub

	}

}
