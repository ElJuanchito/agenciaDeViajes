package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.controllers.PeticionController;
import co.edu.uniquindio.agenciaviajes.controllers.TipoPeticion;
import co.edu.uniquindio.agenciaviajes.controllers.TipoVista;
import co.edu.uniquindio.agenciaviajes.controllers.VistaManager;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.exceptions.PeticionException;
import co.edu.uniquindio.agenciaviajes.model.Destino;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionarDestinosController implements Controllable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblTitle;

	@FXML
	private TextField txtBuscar;

	@FXML
	private TableView<Destino> tableClientes;

	@FXML
	private TableColumn<Destino, String> colId, colNombre, colCiudad, colDescripcion, colClima;

	@FXML
	private Button btnEliminar, btnAgregar;

	@FXML
	void agregarEvent(ActionEvent event) {
		agregarAction();
	}

	private void agregarAction() {
		MainPaneController.getInstance().ejecutarProceso(() -> {
			try {
				VistaManager.getInstance().cambiarVistaAdmin(TipoVista.AGREGAR_DESTINO, null);
			} catch (FXMLException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void eliminarEvent(ActionEvent event) {

	}

	@Override
	public void preInicializar() {
		colId.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId() + ""));
		colCiudad.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCiudad()));
		colClima.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getClima().getText()));
		colDescripcion.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getDescripcion()));
		colNombre.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNombre()));
		actualizarTabla();
	}

	private void actualizarTabla() {
		MainPaneController.getInstance().ejecutarProceso(() -> {
			try {
				tableClientes.setItems(FXCollections.observableArrayList(
						new PeticionController<Void, List<Destino>>(TipoPeticion.LISTAR_DESTINO, null)
								.realizarPeticion()));
				tableClientes.refresh();
			} catch (PeticionException e1) {
				e1.printStackTrace();
			}
		});
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		lblTitle.setText(bundle.getString("GestionarDestinosController.lblTitle"));
		txtBuscar.setPromptText(bundle.getString("GestionarDestinosController.txtBuscar"));
		colId.setText(bundle.getString("GestionarDestinosController.colId"));
		colNombre.setText(bundle.getString("GestionarDestinosController.colNombre"));
		colCiudad.setText(bundle.getString("GestionarDestinosController.colCiudad"));
		colDescripcion.setText(bundle.getString("GestionarDestinosController.colDescripcion"));
		colClima.setText(bundle.getString("GestionarDestinosController.colClima"));
		btnEliminar.setText(bundle.getString("GestionarDestinosController.btnEliminar"));
		btnAgregar.setText(bundle.getString("GestionarDestinosController.btnAgregar"));
	}

	@Override
	public void clearData() {
		actualizarTabla();
	}
}
