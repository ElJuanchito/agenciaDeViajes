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

public class SolicitudGuiaController implements Controllable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblTitle;

	@FXML
	private TextField txtBuscar;

	@FXML
	private TableView<GuiaTuristico> tableGuias;

	@FXML
	private TableColumn<GuiaTuristico, String> colNombre, colIdentificacion, colHorasExperiencia, colIdiomas;

	@FXML
	private Button btnAceptar, btnDenegar;

	@FXML
	void aceptarEvent(ActionEvent event) {

	}

	@FXML
	void denegarEvent(ActionEvent event) {

	}

	@Override
	public void preInicializar() {
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		lblTitle.setText(bundle.getString("SolicitudGuiaController.lblTitle"));
		txtBuscar.setText(bundle.getString("SolicitudGuiaController.txtBuscar"));
		colNombre.setText(bundle.getString("SolicitudGuiaController.colNombre"));
		colIdentificacion.setText(bundle.getString("SolicitudGuiaController.colIdentificacion"));
		colHorasExperiencia.setText(bundle.getString("SolicitudGuiaController.colHorasExperiencia"));
		colIdiomas.setText(bundle.getString("SolicitudGuiaController.colIdiomas"));
		btnAceptar.setText(bundle.getString("SolicitudGuiaController.btnAceptar"));
		btnDenegar.setText(bundle.getString("SolicitudGuiaController.btnDenegar"));

	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub

	}
}
