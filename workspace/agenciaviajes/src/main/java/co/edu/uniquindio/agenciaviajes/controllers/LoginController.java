package co.edu.uniquindio.agenciaviajes.controllers;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class LoginController implements DataControllable<String> {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
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

	private File[] files;

	private List<File> songs;

	private Media media;

	private MediaPlayer mediaPlayer;

	@FXML
	void iniciarEvent(ActionEvent event) {
		iniciarAction();
	}

	private void iniciarAction() {
		MainPaneController.getInstance().ejecutarProceso(() -> {
			try {
				VistaManager.getInstance().cambiarVista(TipoVista.PAQUETE_DETAILS, null);
			} catch (FXMLException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void registrarEvent(ActionEvent event) {
		MainPaneController.getInstance().ejecutarProceso(() -> {
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
		DJPerdomo();
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

	private void DJPerdomo() {

		files = new File("src/main/resources/co/edu/uniquindio/agenciaViajes/media/songs").listFiles();

		songs = Arrays.asList(files);

		Random random = new Random();
		int randomIndex = random.nextInt(songs.size());

		media = new Media(songs.get(randomIndex).toURI().toString());

		mediaPlayer = new MediaPlayer(media);

		mediaPlayer.play();

	}

}
