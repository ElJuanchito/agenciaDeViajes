package co.edu.uniquindio.agenciaViajes.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaViajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaViajes.services.AnimationService;
import co.edu.uniquindio.agenciaViajes.services.Controllable;
import co.edu.uniquindio.agenciaViajes.ui.TipoVista;
import co.edu.uniquindio.agenciaViajes.ui.VistaManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginController implements Controllable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView pikachuImg;

	@FXML
	private Button btnBack;

	@FXML
	private Label lblTitle;

	@FXML
	private Label lblBienvenido;

	@FXML
	private TextField txtUsuario;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Button btnIniciar;

	@FXML
	private Label lblNoRegistrado;

	@FXML
	private Button btnRegistrar;

	@FXML
	void backEvent(ActionEvent event) {
	}

	@FXML
	void iniciarEvent(ActionEvent event) {
		AnimationService.getInstance().ejecutarAccionBtn(btnIniciar, () -> {
			try {
				VistaManager.getInstance().cambiarVista(TipoVista.DESTINOS, null);
			} catch (FXMLException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void passwordEvent(ActionEvent event) {

	}

	@FXML
	void registrarEvent(ActionEvent event) {

	}

	@FXML
	void usuarioEvent(ActionEvent event) {

	}

	@FXML
	void initialize() {

	}

	@Override
	public void preInicializar() {

	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {

	}

	@Override
	public void clearData() {

	}
}
