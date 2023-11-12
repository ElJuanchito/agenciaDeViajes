package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class EditProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox root;

    @FXML
    private ImageView imageProfile;

    @FXML
    private Button btnChangeImage;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblInfo;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtDireccion;

    @FXML
    private Button btnRegistro;

    @FXML
    void changeImageEvent(ActionEvent event) {

    }

    @FXML
    void guardarEvent(ActionEvent event) {

    }

    @FXML
    void volverEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
}
