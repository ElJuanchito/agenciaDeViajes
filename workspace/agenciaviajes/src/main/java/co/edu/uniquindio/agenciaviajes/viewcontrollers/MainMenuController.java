package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.controllers.DataController;
import co.edu.uniquindio.agenciaviajes.controllers.TipoVista;
import co.edu.uniquindio.agenciaviajes.controllers.VistaManager;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.exceptions.MovimientoIndefinidoException;
import co.edu.uniquindio.agenciaviajes.model.Cliente;
import co.edu.uniquindio.agenciaviajes.model.Loginable;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
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
	private Circle circleImage;

	@FXML
	private Label lblBtnDestinos, lblBtnGuias, lblbtnPaquetes;

	@FXML
	private ImageView imageViewPerfil;
	@FXML
	private ScrollPane scrollCenter;

	@FXML
	private BorderPane capaMenu, capaMenu2;

	@FXML
	private VBox menuCliente1, menuCliente2;

	private boolean isMenuExtended, isMenu2Extended;
	private Timeline timelineMenu, timelineMenu2;

	private Circle clip;

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
	void verPerfilEvent(MouseEvent event) {
	}

	@FXML
	void modificarPerfilEvent(MouseEvent event) {
	}

	@FXML
	void cerrarSesionEvent(MouseEvent event) {
		cerrarSesionAcion();
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
	void searchEvent(ActionEvent event) {
		loginAction();
	}

	@FXML
	void capaMenuClickedEvent(MouseEvent event) {
		ejecutarAnimacionMenuNoLogin();
	}

	@FXML
	void capaMenu2ClickedEvent(MouseEvent event) {
		ejecutarAnimacionMenuCliente();
	}

	@FXML
	void loginEvent(MouseEvent event) {
		loginAction();
	}

	@FXML
	void registerEvent(MouseEvent event) {
		registerAction();
	}

	@FXML
	void paquetesEvent(ActionEvent event) {
		paquetesAction();
	}

	@Override
	public void preInicializar() {
		clip = new Circle(21);
		clip.setCenterX(43);
		clip.setCenterY(24.5);
		imageViewPerfil.setFitHeight(86);
		imageViewPerfil.setFitWidth(86);
		imageViewPerfil.setClip(clip);
		actualizarImgLogin(DataController.getInstance().getLoginActualValue());
		DataController.getInstance().getLoginActual().addListener((observable, oldValue, newValue) -> {
			actualizarImgLogin(newValue);
		});

		timelineMenu = crearAnimacionExtension(menuCliente1.prefWidthProperty(), capaMenu.opacityProperty());
		timelineMenu2 = crearAnimacionExtension(menuCliente2.prefWidthProperty(), capaMenu2.opacityProperty());
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

	private void actualizarImgLogin(Loginable newValue) {
		if (DataController.getInstance().usuarioEsCliente()) {
			Cliente cliente = (Cliente) newValue;
			if (cliente.getImagen() != null) {
				imageViewPerfil.setImage(cliente.getImagen().getImage());
				imageViewPerfil.setOpacity(1);
			} else {
				imageViewPerfil.setOpacity(0);
			}
		} else {
			imageViewPerfil.setOpacity(0);
		}
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

	private void loginAction() {
		cambiarVistaLoginReg(TipoVista.LOGIN);
	}

	private void registerAction() {
		cambiarVistaLoginReg(TipoVista.REGISTRO);
	}

	private void perfilClickAction() {
		ejecutarAnimacionMenu();
	}

	private void cambiarVistaLoginReg(TipoVista tipoVista) {
		MainPaneController.getInstance().ejecutarProceso(() -> {
			try {
				VistaManager.getInstance().cambiarVista(tipoVista, new Pair<Runnable, String>(() -> {
					try {
						VistaManager.getInstance().cambiarVista(TipoVista.MENU_PRINCIPAL_CLIENTE, null);
					} catch (FXMLException e) {
						throw new RuntimeException(e);
					}
				}, ""));
			} catch (FXMLException e) {
				e.printStackTrace();
			}
		});
	}

	private void guiasAction() {
		MainPaneController.getInstance().ejecutarProceso(() -> {
			try {
				//Cambie para que se vea la tabla de guias
				VistaManager.getInstance().cambiarVistaCliente(TipoVista.GUIAS, null);
			} catch (FXMLException e) {
				throw new RuntimeException(e);
			}
		});
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

	private Timeline crearAnimacionExtension(DoubleProperty widthProperty, DoubleProperty opacityProperty) {
		Timeline timelineMenu = new Timeline();
		timelineMenu.getKeyFrames().add(
				new KeyFrame(Duration.millis(0), new KeyValue(widthProperty, 0d), new KeyValue(opacityProperty, 0d)));
		timelineMenu.getKeyFrames().add(new KeyFrame(Duration.millis(100), new KeyValue(opacityProperty, 1d),
				new KeyValue(widthProperty, 150d)));
		return timelineMenu;
	}

	private void ejecutarAnimacionMenu() {
		if (DataController.getInstance().usuarioEsCliente())
			ejecutarAnimacionMenuCliente();
		else
			ejecutarAnimacionMenuNoLogin();

	}

	private void ejecutarAnimacionMenuCliente() {
		capaMenu2.setDisable(isMenu2Extended);
		if (isMenu2Extended) {
			timelineMenu2.stop();
			timelineMenu2.setRate(-1);
			timelineMenu2.jumpTo(Duration.millis(100));
			timelineMenu2.play();
		} else {
			timelineMenu2.playFromStart();
		}
		isMenu2Extended = !isMenu2Extended;
	}

	private void ejecutarAnimacionMenuNoLogin() {
		capaMenu.setDisable(isMenuExtended);
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

	private void cerrarSesionAcion() {
		DataController.getInstance().selectUsuario(null);
	}
}
