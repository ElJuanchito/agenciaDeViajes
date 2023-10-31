package co.edu.uniquindio.agenciaviajes.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.model.GuiaTuristico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionarGuiasController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblTitle;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TableView<GuiaTuristico> tableClientes;

    @FXML
    private TableColumn<GuiaTuristico, String> colNombre;

    @FXML
    private TableColumn<GuiaTuristico, String> colIdentificacion;

    @FXML
    private TableColumn<GuiaTuristico, String> colHorasExperiencia;

    @FXML
    private TableColumn<GuiaTuristico, String> colIdiomas;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnSolicitudes;

    @FXML
    void solicitudesEvent(ActionEvent event) {

    }

    @FXML
    void eliminarEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	

    }
}
