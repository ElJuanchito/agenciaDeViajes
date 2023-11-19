package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.io.File;
import java.net.URL;
import java.util.*;

import co.edu.uniquindio.agenciaviajes.controllers.DataController;
import co.edu.uniquindio.agenciaviajes.controllers.PeticionController;
import co.edu.uniquindio.agenciaviajes.controllers.TipoPeticion;
import co.edu.uniquindio.agenciaviajes.controllers.TipoVista;
import co.edu.uniquindio.agenciaviajes.controllers.VistaManager;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.exceptions.PeticionException;
import co.edu.uniquindio.agenciaviajes.model.Loginable;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
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
import javafx.util.Pair;

public class LoginController implements DataControllable<Pair<Runnable, String>> {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	@FXML
	private Button btnIniciar, btnRegistrar;

	@FXML
	private Label lblInfo, lblNoRegistrado, lblTitle;

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

	private Runnable volverRunnable;

	@FXML
	void iniciarEvent(ActionEvent event) {
		iniciarAction();
	}

	private void iniciarAction() {
		MainPaneController.getInstance().ejecutarProceso(this::iniciarSesion);
	}

	private void iniciarSesion() {
		realizarPeticionLogin(new PeticionController<Loginable, Loginable>(TipoPeticion.HACER_LOGIN, loadInfo()));
	}

	private void realizarPeticionLogin(PeticionController<Loginable, Loginable> peticionController) {
		try {
			Loginable loginable = peticionController.realizarPeticion();
			DataController.getInstance().selectUsuario(loginable);
			VistaManager.getInstance()
					.cambiarVista(DataController.getInstance().usuarioEsCliente() ? TipoVista.MENU_PRINCIPAL_CLIENTE
							: TipoVista.MENU_PRINCIPAL_ADMIN, null);
		} catch (PeticionException | FXMLException e) {
			MainPaneController.getInstance().showAlert(e.getMessage());
		}
	}

	private Loginable loadInfo() {
		return new Loginable() {

			@Override
			public String getUsuario() {
				return txtEmail.getText();
			}

			@Override
			public String getContrasena() {
				return txtPassword.getText();
			}
		};
	}

	@FXML
	void volverEvent(ActionEvent event) {
		volverAction();
	}

	@FXML
	void registrarEvent(ActionEvent event) {
		registrarAction();
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
		lblTitle.setText(bundle.getString("LoginController.lblTitle"));
		lblInfo.setText(bundle.getString("LoginController.lblInfo"));
		txtEmail.setPromptText(bundle.getString("LoginController.txtEmail"));
		txtPassword.setPromptText(bundle.getString("LoginController.txtPassword"));
		btnIniciar.setText(bundle.getString("LoginController.btnIniciar"));
		lblNoRegistrado.setText(bundle.getString("LoginController.lblNoRegistrado"));
		btnRegistrar.setText(bundle.getString("LoginController.btnRegistrar"));
	}

	@Override
	public void clearData() {
		txtEmail.clear();
		txtPassword.clear();
	}

	private void DJPerdomo() {

		files = new File(Objects.requireNonNull(getClass().getResource("/")).getFile()).listFiles((dir, nombre) -> nombre.toLowerCase().endsWith(".mp3"));
        assert files != null;
        songs = Arrays.asList(files);

		Random random = new Random();
		int randomIndex = random.nextInt(songs.size());

		media = new Media(songs.get(randomIndex).toURI().toString());

		mediaPlayer = new MediaPlayer(media);

		mediaPlayer.play();

	}

	@Override
	public void inicializarDatos(Pair<Runnable, String> dato) {
		if (dato == null) {
			clearData();
			return;
		}
		String s = dato.getValue();
		txtEmail.setText(s == null ? "" : s);
		volverRunnable = dato.getKey();
	}

	private void registrarAction() {
		MainPaneController.getInstance().ejecutarProceso(() -> {
			try {
				VistaManager.getInstance().cambiarVista(TipoVista.REGISTRO,
						new Pair<Runnable, String>(volverRunnable, txtEmail.getText()));
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

}
