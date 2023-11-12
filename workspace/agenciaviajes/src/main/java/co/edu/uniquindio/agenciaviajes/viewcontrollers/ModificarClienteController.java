package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.controllers.DataController;
import co.edu.uniquindio.agenciaviajes.controllers.PeticionController;
import co.edu.uniquindio.agenciaviajes.controllers.TipoPeticion;
import co.edu.uniquindio.agenciaviajes.exceptions.PeticionException;
import co.edu.uniquindio.agenciaviajes.model.Cliente;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;

public class ModificarClienteController implements DataControllable<Pair<Runnable, String>> {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private HBox root;

	@FXML
	private StackPane stackImg;

	@FXML
	private Button btnBack, btnActualizar;

	@FXML
	private Label lblTitle, lblInfo;

	@FXML
	private TextField txtIdentificacion, txtNombre, txtEmail, txtTelefono, txtDireccion;
	@FXML
	private PasswordField txtPassword;

	private Runnable runnableVolver;

	@FXML
	void actualizarEvent(ActionEvent event) {
		actualizarAction();
	}

	@FXML
	void backEvent(ActionEvent event) {
		if (runnableVolver != null)
			runnableVolver.run();
	}

	@Override
	public void preInicializar() {
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		lblTitle.setText(bundle.getString("ModificarClienteController.lblTitle"));
		lblInfo.setText(bundle.getString("ModificarClienteController.lblInfo"));
		txtIdentificacion.setPromptText(bundle.getString("ModificarClienteController.txtIdentificacion"));
		txtNombre.setPromptText(bundle.getString("ModificarClienteController.txtNombre"));
		txtPassword.setPromptText(bundle.getString("ModificarClienteController.txtPassword"));
		txtEmail.setPromptText(bundle.getString("ModificarClienteController.txtEmail"));
		txtTelefono.setPromptText(bundle.getString("ModificarClienteController.txtTelefono"));
		txtDireccion.setPromptText(bundle.getString("ModificarClienteController.txtDireccion"));
		btnActualizar.setText(bundle.getString("ModificarClienteController.btnRegistro"));
	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inicializarDatos(Pair<Runnable, String> dato) {
		runnableVolver = dato.getKey();
		if (DataController.getInstance().usuarioEsCliente()) {
			Cliente cliente = (Cliente) DataController.getInstance().getLoginActual();
			txtIdentificacion.setText(cliente.getIdentificacion());
			txtNombre.setText(cliente.getNombreCompleto());
			txtPassword.setText("");
			txtEmail.setText(cliente.getEmail());
			txtTelefono.setText(cliente.getTelefono());
			txtDireccion.setText(cliente.getDireccion());
		}

	}

	private void actualizarAction() {
		try {
			new PeticionController<Cliente, Boolean>(TipoPeticion.ACTUALIZAR_CLIENTE,
					Cliente.builder().nombreCompleto(txtNombre.getText()).telefono(txtTelefono.getText())
							.identificacion(txtIdentificacion.getText()).email(txtEmail.getText())
							.contrasena(txtPassword.getText()).direccion(txtDireccion.getText()).build())
					.realizarPeticion();
		} catch (PeticionException e) {
			MainPaneController.getInstance().showAlert(e.getMessage());
		}
	}

}
