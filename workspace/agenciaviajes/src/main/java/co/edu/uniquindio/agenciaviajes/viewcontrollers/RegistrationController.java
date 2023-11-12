package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.controllers.TipoVista;
import co.edu.uniquindio.agenciaviajes.controllers.VistaManager;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;

public class RegistrationController implements DataControllable<Pair<Runnable, String>> {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnBack, btnLogin, btnRegistro;

	@FXML
	private Label lblInfo, lblTitle, lblYaRegistrado;

	@FXML
	private ImageView pikachuImg;

	@FXML
	private TextField txtDireccion, txtEmail, txtIdentificacion, txtNombre, txtTelefono;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private HBox root;

	@FXML
	private StackPane stackImg;

	private Runnable volverRunnable;

	@FXML
	void volverEvent(ActionEvent event) {
		volverAction();
	}

	@FXML
	void loginEvent(ActionEvent event) {
		loginAction();
	}

	@FXML
	void registroEvent(ActionEvent event) {
		registroAction();
	}

	@Override
	public void preInicializar() {
		stackImg.prefWidthProperty().bind(root.heightProperty().multiply(0.75d));
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		txtIdentificacion.setText(bundle.getString("RegistrationController.txtIdentificacion"));
		txtNombre.setPromptText(bundle.getString("RegistrationController.txtNombre"));
		txtPassword.setPromptText(bundle.getString("RegistrationController.txtPassword"));
		txtEmail.setPromptText(bundle.getString("RegistrationController.txtEmail"));
		txtTelefono.setPromptText(bundle.getString("RegistrationController.txtTelefono"));
		txtDireccion.setPromptText(bundle.getString("RegistrationController.txtDireccion"));
		btnRegistro.setText(bundle.getString("RegistrationController.btnRegistro"));
		lblYaRegistrado.setText(bundle.getString("RegistrationController.lblYaRegistrado"));
		btnLogin.setText(bundle.getString("RegistrationController.btnLogin"));
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
	public void inicializarDatos(Pair<Runnable, String> dato) {
		if (dato == null) {
			clearData();
			return;
		}
		volverRunnable = dato.getKey();
		String s = dato.getValue();
		txtIdentificacion.setText(s == null ? "" : s);

	}

	private void loginAction() {
		MainPaneController.getInstance().ejecutarProceso(() -> {
			try {
				VistaManager.getInstance().cambiarVista(TipoVista.LOGIN,
						new Pair<Runnable, String>(volverRunnable, txtIdentificacion.getText()));
			} catch (FXMLException e) {
				e.printStackTrace();
			}
		});
	}

	private void volverAction() {
		if (volverRunnable == null)
			return;
		volverRunnable.run();
	}

	private void registroAction() {
		MainPaneController.getInstance().showAlert("Registro :)");
	}

}
