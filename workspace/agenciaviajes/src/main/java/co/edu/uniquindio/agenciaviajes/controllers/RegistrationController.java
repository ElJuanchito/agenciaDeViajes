package co.edu.uniquindio.agenciaviajes.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.services.AnimationService;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
import co.edu.uniquindio.agenciaviajes.ui.TipoVista;
import co.edu.uniquindio.agenciaviajes.ui.VistaManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class RegistrationController implements DataControllable<String> {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnRegistro;

	@FXML
	private Label lblInfo;

	@FXML
	private Label lblTitle;

	@FXML
	private Label lblYaRegistrado;

	@FXML
	private ImageView pikachuImg;

	@FXML
	private TextField txtDireccion;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtIdentificacion;

	@FXML
	private TextField txtNombre;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private TextField txtTelefono;

	@FXML
	private HBox root;

	@FXML
	private StackPane stackImg;

	@FXML
	void backEvent(ActionEvent event) {

	}

	@FXML
	void loginEvent(ActionEvent event) {
		loginAction();
	}

	private void loginAction() {
		AnimationService.getInstance().ejecutarAccionBtn(btnLogin, () -> {
			try {
				VistaManager.getInstance().cambiarVista(TipoVista.LOGIN, txtIdentificacion.getText());
			} catch (FXMLException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void registroEvent(ActionEvent event) {

	}

	@Override
	public void preInicializar() {
		stackImg.prefWidthProperty().bind(root.heightProperty().multiply(0.75d));
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearData() {
		txtDireccion.clear();
		txtEmail.clear();
		txtNombre.clear();
		txtTelefono.clear();
		txtPassword.clear();
		txtIdentificacion.clear();
	}

	@Override
	public void inicializarDatos(String dato) {
		txtIdentificacion.setText(dato);
	}

}
