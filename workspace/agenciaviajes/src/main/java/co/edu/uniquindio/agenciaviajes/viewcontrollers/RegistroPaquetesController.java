package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.model.Destino;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import co.edu.uniquindio.agenciaviajes.utils.UtilsFX;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class RegistroPaquetesController implements Controllable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnRegistrar;

	@FXML
	private TableColumn<Destino, String> colCiudad;

	@FXML
	private TableColumn<Destino, String> colClima;

	@FXML
	private TableColumn<Destino, String> colDescripcion;

	@FXML
	private TableColumn<Destino, String> colId;

	@FXML
	private TableColumn<Destino, String> colIdSelect;

	@FXML
	private TableColumn<Destino, String> colNombre;

	@FXML
	private TableColumn<Destino, String> colNombreSelect;

	@FXML
	private DatePicker dpFin;

	@FXML
	private DatePicker dpInicio;

	@FXML
	private Label lblCupo;

	@FXML
	private Label lblDuracion;

	@FXML
	private Label lblFechaFin;

	@FXML
	private Label lblFechaInicio;

	@FXML
	private Label lblNombre;

	@FXML
	private Label lblPrecio;

	@FXML
	private Label lblSeleccionar;

	@FXML
	private Label lblServicios;

	@FXML
	private Label lblTitulo;

	@FXML
	private TableView<Destino> tblDestinos;

	@FXML
	private TableView<Destino> tblSeleccionados;

	@FXML
	private TextField txtCupo;

	@FXML
	private TextField txtDuracion;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtPrecio;

	@FXML
	private TextField txtServicios;

	private ObservableList<Destino> listDestinos;
	private ObservableList<Destino> listSeleccionados;

	@FXML
	void deseleccionarDestinoEvent(MouseEvent event) {
		deseleccionarDestinoAction();
	}

	@FXML
	void registrarEvent(ActionEvent event) {
		registrarAction();
	}

	@FXML
	void seleccionarDestinoEvent(MouseEvent event) {
		seleccionarDestinoAction();
	}

	private void seleccionarDestinoAction() {
		Destino aux = tblDestinos.getSelectionModel().getSelectedItem();
		if (aux != null) {
			listDestinos.removeAll(aux);
			listSeleccionados.addAll(aux);

			tblDestinos.refresh();
			tblSeleccionados.refresh();
		}
	}

	private void registrarAction() {
		// TODO Auto-generated method stub

	}

	private void deseleccionarDestinoAction() {
		Destino aux = tblSeleccionados.getSelectionModel().getSelectedItem();
		if (aux != null) {
			listSeleccionados.removeAll(aux);
			listDestinos.addAll(aux);

			tblSeleccionados.refresh();
			tblDestinos.refresh();
			
		}

	}

	@Override
	public void preInicializar() {
		// ***************************************************************
		// listDestinos = FXCollections.observableArrayList();           *
		// listSeleccionados = FXCollections.observableArrayList();      *
		//                                                               *
		// TODO Todavia se ha definido si utilizadoremos una clase       *
		// adminsitradora clasica (AgenciaViajes) o si utiliza           *
		// una clase tipo manejadora de entidades                        *
		//                                                               *
		// ***************************************************************
		
		UtilsFX.setAsNameTextField(txtNombre);
		UtilsFX.setAsIntegerTextfield(txtDuracion);
		UtilsFX.setAsNumberTextfield(txtDuracion);
		UtilsFX.setAsIntegerTextfield(txtCupo);
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub

	}
}
