package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SolicitudGuiaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblTitle;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TableView<?> tableClientes;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colIdentificacion;

    @FXML
    private TableColumn<?, ?> colHorasExperiencia;

    @FXML
    private TableColumn<?, ?> colIdiomas;

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnDenegar;

    @FXML
    void aceptarEvent(ActionEvent event) {

    }

    @FXML
    void denegarEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	
    }
}
