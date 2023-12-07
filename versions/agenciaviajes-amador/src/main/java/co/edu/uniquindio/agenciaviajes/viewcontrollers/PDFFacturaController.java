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
			lblTelefonoCliente, lblTipoAtencion, lblTranatemientoAtencion, lblVeterinarioAtencion, lblEstado,
			lblVeterinario, lblMascota, lblTipo, lblSexo, lblDiagnostico, lblTratamiento, lblPrecio, lblLema,
			lblFacturaPara, lblTelefono, lblDireccion, lblFactura, lblThx;

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		lblEstado.setText(bundle.getString("PDFFacturaController.lblEstado"));
		lblVeterinario.setText(bundle.getString("PDFFacturaController.lblVeterinario"));
		lblMascota.setText(bundle.getString("PDFFacturaController.lblMascota"));
		lblTipo.setText(bundle.getString("PDFFacturaController.lblTipo"));
		lblSexo.setText(bundle.getString("PDFFacturaController.lblSexo"));
		lblDiagnostico.setText(bundle.getString("PDFFacturaController.lblDiagnostico"));
		lblTratamiento.setText(bundle.getString("PDFFacturaController.lblTratamiento"));
		lblPrecio.setText(bundle.getString("PDFFacturaController.lblPrecio"));
		lblLema.setText(bundle.getString("PDFFacturaController.lblLema"));
		lblFacturaPara.setText(bundle.getString("PDFFacturaController.lblFacturaPara"));
		lblTelefono.setText(bundle.getString("PDFFacturaController.lblTelefono"));
		lblDireccion.setText(bundle.getString("PDFFacturaController.lblDireccion"));
		lblFactura.setText(bundle.getString("PDFFacturaController.lblFactura"));
		lblThx.setText(bundle.getString("PDFFacturaController.lblThx"));

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
