package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.application.App;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.SVGPath;

public class MenuPrincipalAdminController implements Controllable {

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
		cambiarVentana("gestionarDestinos");

	}

	@FXML
	void extraEvent(ActionEvent event) {

	}

	@FXML
	void guiasEvent(ActionEvent event) {
		cambiarVentana("gestionarGuias");

	}

	@FXML
	void masEvent(ActionEvent event) {

	}

	@FXML
	void nextEvent(ActionEvent event) {

	}

	@FXML
	void paquetesEvent(ActionEvent event) {
		cambiarVentana("gestionarPaquetes");

	}

	private void cambiarVentana(String fxmlname) {
		try {
			Node nodo = App.loadFXML(fxmlname);
			setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setCenter(Node node) {
		centerPane.setCenter(node);
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

}
