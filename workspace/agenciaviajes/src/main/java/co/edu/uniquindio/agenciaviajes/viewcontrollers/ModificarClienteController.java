package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ModificarClienteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox root;

    @FXML
    private StackPane stackImg;

    @FXML
    private Button btnBack;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblInfo;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtDireccion;

    @FXML
    private Button btnActualizar;

    @FXML
    void actualizarEvent(ActionEvent event) {

    }

    @FXML
    void backEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
}
