package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.controllers.PeticionController;
import co.edu.uniquindio.agenciaviajes.controllers.TipoPeticion;
import co.edu.uniquindio.agenciaviajes.exceptions.PeticionException;
import co.edu.uniquindio.agenciaviajes.model.Cliente;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.util.Pair;

public class EditProfileController implements DataControllable<Pair<Runnable, Cliente>> {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private HBox root;

	@FXML
	private ImageView imageProfile;

	@FXML
	private Label lblTitle, lblInfo;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private TextField txtIdentificacion, txtNombre, txtEmail, txtTelefono, txtDireccion;

	@FXML
	private Button btnChangeImage, btnRegistro;

	private Runnable runnableVolver;

	@FXML
	void changeImageEvent(ActionEvent event) {
		changeImageAction();
	}

	private void changeImageAction() {
		FileChooser fChooser = new FileChooser();
		fChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagenes", "*.png", "*.jpg", "*.gif"),
				new FileChooser.ExtensionFilter("Todos los archivos", "*.*"));
		File file = fChooser.showOpenDialog(null);
		if (file != null) {
			String ruta = file.toURI().toString();
			var imagencita = new Image(ruta);
			Platform.runLater(() -> imageProfile.setImage(imagencita));
		} else {
			MainPaneController.getInstance().showAlert("No se ha seleccionado ninguna imagen");
		}
	}

	@FXML
	void guardarEvent(ActionEvent event) {
		guardarAction();
	}

	private void guardarAction() {
		try {
			new PeticionController<Cliente, Cliente>(TipoPeticion.ACTUALIZAR_CLIENTE,
					Cliente.builder().contrasena(txtPassword.getText()).identificacion(txtIdentificacion.getText())
							.nombreCompleto(txtNombre.getText()).email(txtEmail.getText())
							.telefono(txtTelefono.getText()).direccion(txtDireccion.getText()).build())
					.realizarPeticion();
			MainPaneController.getInstance().showAlert("Se ha guardado la información con éxito");
		} catch (PeticionException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void volverEvent(ActionEvent event) {
		volverAction();
	}

	private void volverAction() {
		if (runnableVolver != null)
			runnableVolver.run();
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		txtIdentificacion.setText(bundle.getString("RegistrationController.txtIdentificacion"));
		txtNombre.setPromptText(bundle.getString("RegistrationController.txtNombre"));
		txtPassword.setPromptText(bundle.getString("RegistrationController.txtPassword"));
		txtEmail.setPromptText(bundle.getString("RegistrationController.txtEmail"));
		txtTelefono.setPromptText(bundle.getString("RegistrationController.txtTelefono"));
		txtDireccion.setPromptText(bundle.getString("RegistrationController.txtDireccion"));
		btnRegistro.setText("Guardar");
		lblTitle.setText("¡Edita tu perfil!");
		lblInfo.setText("Edita tu perfil y continua disfrutando de nuestra aplicacion");
		txtDireccion.setPromptText(bundle.getString("RegistrationController.txtDireccion"));

	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void preInicializar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inicializarDatos(Pair<Runnable, Cliente> info) {
		runnableVolver = info.getKey();
		Cliente cliente = info.getValue();
		txtIdentificacion.setText(cliente.getIdentificacion());
		txtNombre.setText(cliente.getNombreCompleto());
		txtPassword.setText("");
		txtEmail.setText(cliente.getEmail());
		txtTelefono.setText(cliente.getTelefono());
		txtDireccion.setText(cliente.getDireccion());
		Image image = cliente.getImagen().getImage();
		if (image != null) {
			imageProfile.setImage(image);
		}

	}
}
