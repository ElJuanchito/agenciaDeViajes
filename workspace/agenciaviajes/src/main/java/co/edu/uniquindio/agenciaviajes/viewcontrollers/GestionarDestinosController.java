package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.model.Destino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionarDestinosController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblTitle;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TableView<Destino> tableClientes;

    @FXML
    private TableColumn<Destino, String> colId;

    @FXML
    private TableColumn<Destino, String> colNombre;

    @FXML
    private TableColumn<Destino, String> colCiudad;

    @FXML
    private TableColumn<Destino, String> colDescripcion;

    @FXML
    private TableColumn<Destino, String> colClima;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnAgregar;

    @FXML
    void agregarEvent(ActionEvent event) {

    }

    @FXML
    void eliminarEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
}
