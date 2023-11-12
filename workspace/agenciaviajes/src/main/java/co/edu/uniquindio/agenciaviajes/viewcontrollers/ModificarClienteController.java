package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.services.Controllable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ModificarClienteController implements Controllable {

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

	@FXML
	void actualizarEvent(ActionEvent event) {

	}

	@FXML
	void backEvent(ActionEvent event) {

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

}
