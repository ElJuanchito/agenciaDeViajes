package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.util.ResourceBundle;
import java.util.function.Consumer;

import co.edu.uniquindio.agenciaviajes.controllers.TipoVista;
import co.edu.uniquindio.agenciaviajes.controllers.VistaManager;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.exceptions.MovimientoIndefinidoException;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;
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

	private boolean isMenuExtended = false;
	private Timeline timelineMenu;
	private Consumer<Boolean> consumerMenu;

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
		lblBtnDestinos.setText(bundle.getString("MainMenuController.lblBtnDestinos"));
		lblbtnPaquetes.setText(bundle.getString("MainMenuController.lblbtnPaquetes"));
		lblBtnGuias.setText(bundle.getString("MainMenuController.lblBtnGuias"));
	}

	@Override
	public void clearData() {
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
				VistaManager.getInstance().cambiarVistaCliente(TipoVista.PAQUETES, null);
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

	public void crearAnimacionExtension(DoubleProperty widthProperty, DoubleProperty opacityProperty,
			DoubleProperty rotacionSVG, Consumer<Boolean> consumerMenu) {
		timelineMenu = new Timeline();
		timelineMenu.getKeyFrames().add(new KeyFrame(Duration.millis(0), new KeyValue(widthProperty, 0d),
				new KeyValue(opacityProperty, 0d), new KeyValue(rotacionSVG, 0d)));
		timelineMenu.getKeyFrames().add(new KeyFrame(Duration.millis(100), new KeyValue(opacityProperty, 1d),
				new KeyValue(widthProperty, 212d), new KeyValue(rotacionSVG, 90d)));
		this.consumerMenu = consumerMenu;
	}

	public void ejecutarAnimacionMenu() {
		consumerMenu.accept(isMenuExtended);
		if (isMenuExtended) {
			timelineMenu.stop();
			timelineMenu.setRate(-1);
			timelineMenu.jumpTo(Duration.millis(100));
			timelineMenu.play();
		} else {
			timelineMenu.playFromStart();
		}
		isMenuExtended = !isMenuExtended;
	}

}
