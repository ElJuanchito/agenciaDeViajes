package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.controllers.DataController;
import co.edu.uniquindio.agenciaviajes.model.Cliente;
import co.edu.uniquindio.agenciaviajes.model.Loginable;
import co.edu.uniquindio.agenciaviajes.model.Reserva;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
	private TableColumn<Reserva, String> clmFuturasEstado, clmFuturasFechaSoli, clmFuturasGuia, clmFuturasId,
			clmFuturasPaquete, clmFuturasPersonas, clmFuturasPrecio, clmPasadasEstado, clmPasadasFechaSoli,
			clmPasadasGuia, clmPasadasId, clmPasadasPaquete, clmPasadasPersonas, clmPasadasPrecio;

	@FXML
	private ImageView imgCliente;

	@FXML
	private Label lblDireccion, lblEmail, lblId, lblInfoDireccion, lblInfoEmail, lblInfoFuturas, lblInfoId,
			lblInfoNombre, lblInfoPasadas, lblInfoTelefono, lblNombre, lblTelefono, lblTitle;

	@FXML
	private TableView<Reserva> tblReservasFuturas, tblReservasPasadas;

	private Cliente cliente;

	@FXML
	void cancelarReservaEvent(ActionEvent event) {
		// TODO cancelar reserva
	}

	@Override
	public void preInicializar() {
		actualizarInfoCliente(DataController.getInstance().getLoginActualValue());
	}

	private void actualizarInfoCliente(Loginable loginable) {
		if (loginable == null || !(loginable instanceof Cliente))
			return;
		cliente = (Cliente) loginable;
		MainPaneController.getInstance().ejecutarProceso(() -> {
			Platform.runLater(() -> {
				lblId.setText(cliente.getIdentificacion());
				imgCliente.setImage(cliente.getImagen().getImage());
				lblNombre.setText(cliente.getNombreCompleto());
				lblEmail.setText(cliente.getEmail());
				lblTelefono.setText(cliente.getTelefono());
				lblDireccion.setText(cliente.getDireccion());
				tblReservasPasadas.setItems(FXCollections.observableArrayList(cliente.getReservasPasadas()));
				tblReservasFuturas.setItems(FXCollections.observableArrayList(cliente.getReservasFuturas()));
				tblReservasPasadas.refresh();
				tblReservasFuturas.refresh();
			});
		});
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
