package co.edu.uniquindio.agenciaViajes.controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaViajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaViajes.services.AnimationService;
import co.edu.uniquindio.agenciaViajes.services.Controllable;
import co.edu.uniquindio.agenciaViajes.ui.TipoVista;
import co.edu.uniquindio.agenciaViajes.ui.VistaManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class LoginController implements Controllable {

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
	private ImageView pikachuImg;

	@FXML
	private TextField txtEmail;

	@FXML
	private PasswordField txtPassword;

	private File directory;

	private File[] files;

	private List<File> songs;

	private Media media;

	private MediaPlayer mediaPlayer;

	@FXML
	void backEvent(ActionEvent event) {

	}

	@FXML
	void iniciarEvent(ActionEvent event) {
		cambiarVentana(btnIniciar, TipoVista.DESTINOS);
	}

	@FXML
	void registrarEvent(ActionEvent event) {
		cambiarVentana(btnRegistrar, TipoVista.REGISTRO);
	}

	private void cambiarVentana(Button btn, TipoVista tipo) {
		AnimationService.getInstance().ejecutarAccionBtn(btn, () -> {
			try {
				VistaManager.getInstance().cambiarVista(tipo, null);
			} catch (FXMLException e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void preInicializar() {

		DJPerdomo();
	}

	private void DJPerdomo() {
		songs = new ArrayList<File>();

		directory = new File("src/main/resources/co/edu/uniquindio/agenciaViajes/media/songs");

		files = directory.listFiles();

		if (files != null) {

			for (File file : files) {

				songs.add(file);
			}
		}
		
		Random random = new Random();
		int randomIndex = random.nextInt(songs.size());

		media = new Media(songs.get(randomIndex).toURI().toString());
		
		mediaPlayer = new MediaPlayer(media);

		mediaPlayer.play();
		
		new Alert(AlertType.CONFIRMATION, "Entre el cielo y la tierra soy el unico Dios").show();
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
