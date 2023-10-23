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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class LoginController implements DataControllable<String> {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	@FXML
	private Button btnBack;

	@FXML
	private Button btnIniciar;

	@FXML
	private Button btnRegistrar;

	@FXML
	private Label lblInfo;

	@FXML
	private Label lblNoRegistrado;

	@FXML
	private Label lblTitle;

	@FXML
	private HBox root;

	@FXML
	private StackPane stackImg;

	@FXML
	private TextField txtEmail;

	@FXML
	private PasswordField txtPassword;

	@FXML
	void backEvent(ActionEvent event) {

	}

	@FXML
	void iniciarEvent(ActionEvent event) {
		iniciarAction();
	}

	private void iniciarAction() {
		AnimationService.getInstance().ejecutarAccionBtn(btnIniciar, () -> {
			try {
				VistaManager.getInstance().cambiarVista(TipoVista.DESTINOS, null);
			} catch (FXMLException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void registrarEvent(ActionEvent event) {
		AnimationService.getInstance().ejecutarAccionBtn(btnIniciar, () -> {
			try {
				VistaManager.getInstance().cambiarVista(TipoVista.REGISTRO, txtEmail.getText());
			} catch (FXMLException e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void preInicializar() {
//		width: 540.0 
//		height: 720.0
//		relacionAspecto: 3:4 || 0.75
		stackImg.prefWidthProperty().bind(root.heightProperty().multiply(0.75d));
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearData() {
		txtEmail.clear();
		txtPassword.clear();
	}

	@Override
	public void inicializarDatos(String dato) {
		if (dato == null)
			clearData();
		else {
			txtEmail.setText(dato);
		}
	}

}
