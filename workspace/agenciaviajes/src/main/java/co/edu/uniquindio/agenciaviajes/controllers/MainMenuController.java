package co.edu.uniquindio.agenciaviajes.controllers;

import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import co.edu.uniquindio.agenciaviajes.ui.TipoVista;
import co.edu.uniquindio.agenciaviajes.ui.VistaManager;
import co.edu.uniquindio.agenciaviajes.utils.DatosQuemadosAux;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.shape.SVGPath;

public class MainMenuController implements Controllable {

	private static MainMenuController instance;

	public static MainMenuController getInstance() {
		return instance;
	}

	public MainMenuController() {
		instance = this;
	}

	@FXML
	private Button btnBack;

	@FXML
	private Button btnExtra;

	@FXML
	private Button btnNext;

	@FXML
	private SVGPath btnPerfil;

	@FXML
	private Label lblBtnDestinos;

	@FXML
	private Label lblBtnGuias;

	@FXML
	private Label lblbtnPaquetes;

	@FXML
	private ScrollPane scrollCenter;

	@FXML
	void backEvent(ActionEvent event) {

	}

	@FXML
	void destinosEvent(ActionEvent event) {

	}

	@FXML
	void extraEvent(ActionEvent event) {

	}

	@FXML
	void guiasEvent(ActionEvent event) {

	}

	@FXML
	void nextEvent(ActionEvent event) {
		MainPaneController.getInstance().ejecutarProceso(() -> {
			try {
				VistaManager.getInstance().cambiarVistaCliente(TipoVista.PAQUETE_DETAILS,
						DatosQuemadosAux.getInstance().getPaquete());
			} catch (FXMLException e) {
				throw new RuntimeException(e);
			}
		});
	}

	@FXML
	void paquetesEvent(ActionEvent event) {

	}

	public void setContent(Parent parent) {
		scrollCenter.setContent(parent);
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
