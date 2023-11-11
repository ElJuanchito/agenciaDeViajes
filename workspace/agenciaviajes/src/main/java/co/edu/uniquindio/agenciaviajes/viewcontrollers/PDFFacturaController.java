package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.model.Reserva;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PDFFacturaController implements DataControllable<Reserva> {

	@FXML
	private Label lblCodFactura, lblDiagnosticoAtencion, lblDireccionCliente, lblEstadoAtencion, lblFacturaFecha,
			lblFacturaHora, lblMascotaAtencion, lblNombreCliente, lblPrecioAtencion, lblSexoAtencion,
			lblTelefonoCliente, lblTipoAtencion, lblTranatemientoAtencion, lblVeterinarioAtencion;

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		// TODO Auto-generated method stub
	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub
	}

	@Override
	public void inicializarDatos(Reserva dato) {
		// TODO Auto-generated method stub
	}

	@Override
	public void preInicializar() {
		// TODO Auto-generated method stub
	}

}
