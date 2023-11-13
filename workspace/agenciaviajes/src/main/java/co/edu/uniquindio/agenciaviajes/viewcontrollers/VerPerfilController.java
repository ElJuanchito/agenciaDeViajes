package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.model.Reserva;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class VerPerfilController implements Controllable {

    @FXML
    private Button btnCancelarReserva;

    @FXML
    private TableColumn<Reserva, String> clmFuturasEstado;

    @FXML
    private TableColumn<Reserva, String> clmFuturasFechaSoli;

    @FXML
    private TableColumn<Reserva, String> clmFuturasGuia;

    @FXML
    private TableColumn<Reserva, String> clmFuturasId;

    @FXML
    private TableColumn<Reserva, String> clmFuturasPaquete;

    @FXML
    private TableColumn<Reserva, String> clmFuturasPersonas;

    @FXML
    private TableColumn<Reserva, String> clmFuturasPrecio;

    @FXML
    private TableColumn<Reserva, String> clmPasadasEstado;

    @FXML
    private TableColumn<Reserva, String> clmPasadasFechaSoli;

    @FXML
    private TableColumn<Reserva, String> clmPasadasGuia;

    @FXML
    private TableColumn<Reserva, String> clmPasadasId;

    @FXML
    private TableColumn<Reserva, String> clmPasadasPaquete;

    @FXML
    private TableColumn<Reserva, String> clmPasadasPersonas;

    @FXML
    private TableColumn<Reserva, String> clmPasadasPrecio;

    @FXML
    private ImageView imgCliente;

    @FXML
    private Label lblDireccion;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblId;

    @FXML
    private Label lblInfoDireccion;

    @FXML
    private Label lblInfoEmail;

    @FXML
    private Label lblInfoFuturas;

    @FXML
    private Label lblInfoId;

    @FXML
    private Label lblInfoNombre;

    @FXML
    private Label lblInfoPasadas;

    @FXML
    private Label lblInfoTelefono;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblTelefono;

    @FXML
    private Label lblTitle;

    @FXML
    private TableView<Reserva> tblReservasFuturas;

    @FXML
    private TableView<Reserva> tblReservasPasadas;

    @FXML
    void cancelarReservaEvent(ActionEvent event) {

    }

	@Override
	public void preInicializar() {
		// TODO Auto-generated method stub
		
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

