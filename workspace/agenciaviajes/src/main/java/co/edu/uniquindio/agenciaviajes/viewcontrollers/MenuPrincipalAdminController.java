package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.controllers.TipoVista;
import co.edu.uniquindio.agenciaviajes.controllers.VistaManager;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.SVGPath;

public class MenuPrincipalAdminController implements Controllable {
	private static MenuPrincipalAdminController instance;

	public static MenuPrincipalAdminController getInstance() {
		return instance;
	}

	public MenuPrincipalAdminController() {
		instance = this;
	}

	@FXML
	private BorderPane centerPane;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblBtnDestinos, lblBtnClientes, lblbtnPaquetes, lblBtnGuias;

	@FXML
	private SVGPath btnPerfil;

	@FXML
	private ScrollPane scrollCenter;

	@FXML
	private Button btnBack, btnNext, btnExtra;

	@FXML
	void clientesEvent(ActionEvent event) {

	}

	@FXML
	void backEvent(ActionEvent event) {

	}

	@FXML
	void destinosEvent(ActionEvent event) {
		destinosAction();

	}

	@FXML
	void extraEvent(ActionEvent event) {

	}

	@FXML
	void guiasEvent(ActionEvent event) {
		guiasAction();

	}

	@FXML
	void masEvent(ActionEvent event) {

	}

	@FXML
	void nextEvent(ActionEvent event) {

	}

	@FXML
	void paquetesEvent(ActionEvent event) {
		paquetesAction();
	}

	private void cambiarVentana(TipoVista tipoVista) {
		MainPaneController.getInstance().ejecutarProceso(() -> {
			try {
				VistaManager.getInstance().cambiarVistaAdmin(tipoVista, null);
			} catch (FXMLException e) {
				MainPaneController.getInstance().showAlert(e.getMessage());
			}
		});
	}

	@Override
	public void preInicializar() {
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		lblBtnDestinos.setText(bundle.getString("MenuPrincipalAdminController.lblBtnDestinos"));
		lblbtnPaquetes.setText(bundle.getString("MenuPrincipalAdminController.lblbtnPaquetes"));
		lblBtnGuias.setText(bundle.getString("MenuPrincipalAdminController.lblBtnGuias"));
		lblBtnClientes.setText(bundle.getString("MenuPrincipalAdminController.lblBtnClientes"));
	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub
	}

	public void setContent(Parent parent) {
		scrollCenter.setContent(parent);
	}

	private void paquetesAction() {
		cambiarVentana(TipoVista.GESTIONAR_PAQUETES);
	}

	private void destinosAction() {
		cambiarVentana(TipoVista.GESTIONAR_DESTINOS);
	}

	private void guiasAction() {
		cambiarVentana(TipoVista.GESTIONAR_GUIAS);
	}
}
