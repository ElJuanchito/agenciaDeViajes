package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.controllers.TipoVista;
import co.edu.uniquindio.agenciaviajes.controllers.VistaManager;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.exceptions.MovimientoIndefinidoException;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import co.edu.uniquindio.agenciaviajes.utils.DatosQuemadosAux;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import javafx.util.Pair;

public class MainMenuController implements Controllable {

	private static MainMenuController instance;

	public static MainMenuController getInstance() {
		return instance;
	}

	public MainMenuController() {
		instance = this;
	}

	public void setContent(Parent parent) {
		scrollCenter.setContent(parent);
	}

	@FXML
	private Button btnBack, btnRecargar, btnExtra, btnNext;

	@FXML
	private SVGPath btnPerfil;

	@FXML
	private Label lblBtnDestinos, lblBtnGuias, lblbtnPaquetes;

	@FXML
	private ScrollPane scrollCenter;

	@FXML
	void backEvent(ActionEvent event) {
		backAction();
	}

	@FXML
	void destinosEvent(ActionEvent event) {
		destinosAction();
	}

	@FXML
	void reloadEvent(ActionEvent event) {
		reloadAction();
	}

	@FXML
	void extraEvent(ActionEvent event) {

	}

	@FXML
	void guiasEvent(ActionEvent event) {
		guiasAction();
	}

	@FXML
	void nextEvent(ActionEvent event) {
		nextAction();
	}

	@FXML
	void perfilClickEvent(MouseEvent event) {
		perfilClickAction();
	}

	@FXML
	void paquetesEvent(ActionEvent event) {
		paquetesAction();
	}

	@Override
	public void preInicializar() {
		VistaManager.getInstance().getObsAnteriorCliente().addListener((observable, oldValue, newValue) -> {
			btnBack.setDisable(!newValue);
		});
		VistaManager.getInstance().getObsSiguienteCliente().addListener((observable, oldValue, newValue) -> {
			btnNext.setDisable(!newValue);
		});
		VistaManager.getInstance().getVistaActualCliente().addListener((observable, oldValue, newValue) -> {
			btnRecargar.setDisable(newValue == null);
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

	private void perfilClickAction() {
		MainPaneController.getInstance().ejecutarProceso(() -> {
			try {
				VistaManager.getInstance().cambiarVista(TipoVista.LOGIN, new Pair<Runnable, String>(() -> {
					try {
						VistaManager.getInstance().cambiarVista(TipoVista.MENU_PRINCIPAL_CLIENTE, null);
					} catch (FXMLException e) {
						throw new RuntimeException(e);
					}
				}, ""));
			} catch (FXMLException e) {
				throw new RuntimeException(e);
			}
		});
	}

	private void guiasAction() {
		try {
			VistaManager.getInstance().cambiarVistaCliente(TipoVista.REGISTRO_GUIA, null);
		} catch (FXMLException e) {
			throw new RuntimeException(e);
		}
	}

	private void paquetesAction() {
		MainPaneController.getInstance().ejecutarProceso(() -> {
			try {
				VistaManager.getInstance().cambiarVistaCliente(TipoVista.PAQUETE_DETAILS,
						DatosQuemadosAux.getInstance().getPaquete());
			} catch (FXMLException e) {
				throw new RuntimeException(e);
			}
		});
	}

	private void destinosAction() {
		try {
			VistaManager.getInstance().cambiarVistaCliente(TipoVista.DESTINOS, null);
		} catch (FXMLException e) {
			throw new RuntimeException(e);
		}
	}

	private void nextAction() {
		MainPaneController.getInstance().ejecutarProceso(() -> {
			try {
				VistaManager.getInstance().siguienteCliente();
			} catch (FXMLException | MovimientoIndefinidoException e) {
				throw new RuntimeException(e);
			}
		});
	}

	private void reloadAction() {
		MainPaneController.getInstance().ejecutarProceso(() -> {
			try {
				VistaManager.getInstance().reloadCliente();
			} catch (FXMLException e) {
				throw new RuntimeException(e);
			}
		});
	}

	private void backAction() {
		MainPaneController.getInstance().ejecutarProceso(() -> {
			try {
				VistaManager.getInstance().volverCliente();
			} catch (FXMLException | MovimientoIndefinidoException e) {
				throw new RuntimeException(e);
			}
		});
	}

}
