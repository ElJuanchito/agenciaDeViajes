package co.edu.uniquindio.agenciaviajes.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PdfFacturaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblCodFactura;

    @FXML
    private Label lblFacturaFecha;

    @FXML
    private Label lblFacturaHora;

    @FXML
    private Label lblNombreCliente;

    @FXML
    private Label lblTelefonoCliente;

    @FXML
    private Label lblDireccionCliente;

    @FXML
    private Label lblPaquete;

    @FXML
    private Label lblDestinos;

    @FXML
    private Label lblFechaIda;

    @FXML
    private Label lblFechaRegreso;

    @FXML
    private Label lblCantidadPersonas;

    @FXML
    private Label lblIncluyeGuia;

    @FXML
    private Label lblServiciosAdicionales;

    @FXML
    void initialize() {

    }
}
