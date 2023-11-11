package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.model.Paquete;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionarPaquetesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblTitle;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TableView<Paquete> tablePaquetes;

    @FXML
    private TableColumn<Paquete, String> colId;

    @FXML
    private TableColumn<Paquete, String> colNombre;

    @FXML
    private TableColumn<Paquete, String> colDuracion;

    @FXML
    private TableColumn<Paquete, String> colServicios;

    @FXML
    private TableColumn<Paquete, String> colCupoMaximo;

    @FXML
    private TableColumn<Paquete, String> colFechaInicio;

    @FXML
    private TableColumn<Paquete, String> colFechaFin;

    @FXML
    private TableColumn<Paquete, String> colPrecio;

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
