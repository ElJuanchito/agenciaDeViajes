package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.controllers.DataController;
import co.edu.uniquindio.agenciaviajes.controllers.PeticionController;
import co.edu.uniquindio.agenciaviajes.controllers.TipoPeticion;
import co.edu.uniquindio.agenciaviajes.controllers.TipoVista;
import co.edu.uniquindio.agenciaviajes.controllers.VistaManager;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.exceptions.MovimientoIndefinidoException;
import co.edu.uniquindio.agenciaviajes.exceptions.PeticionException;
import co.edu.uniquindio.agenciaviajes.model.Cliente;
import co.edu.uniquindio.agenciaviajes.model.Loginable;
import co.edu.uniquindio.agenciaviajes.model.Paquete;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	private Button btnBack, btnRecargar, btnExtra, btnNext, btnSearch;

	@FXML
	private SVGPath btnPerfil;

	@FXML
	private Circle circleImage;

	@FXML
	private Label lblBtnDestinos, lblBtnGuias, lblbtnPaquetes, lblCerrarSesion, lblLogin, lblRegister, lblVerPerfil;

	@FXML
	private ImageView imageViewPerfil;
	@FXML
	private ScrollPane scrollCenter;

	@FXML
	private BorderPane capaMenu, capaMenu2, searchingLayer;

	@FXML
	private VBox menuCliente1, menuCliente2;

	@FXML
	private TextField txtBuscar;

	private String busquedaAnterior;
	private boolean isMenuExtended, isMenu2Extended, isBusquedaShown;
	private Timeline timelineMenu, timelineMenu2, timelineBusqueda;

	private Circle clip;

	private Color azulOscuro, azulClaro;

	@FXML
	void searchBtnEvent(ActionEvent event) {
		searchBtnAction();
	}

	@FXML
	void txtSearchKeyReleasedEvent(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			enterKeyActionSearch();
		}
		if (event.getCode() == KeyCode.ESCAPE) {
			escapeKeyActionSearch();
		}
	}

	@FXML
	void searchBtnEnterEvent(MouseEvent event) {
		enterKeyActionSearch();
	}

	@FXML
	void closeSearchBtnEvent(MouseEvent event) {
		escapeKeyActionSearch();
	}

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
		clip = new Circle(10.5);
		clip.setCenterX(21.5);
		clip.setCenterY(12);
		imageViewPerfil.setClip(clip);
		azulOscuro = new Color(0.1529, 0.0196, 0.4392, 1.0);
		azulClaro = new Color(0.1529, 0.0196, 0.4392, 0.5);
		btnSearch.setTextFill(azulClaro);
		actualizarImgLogin(DataController.getInstance().getLoginActualValue());
		DataController.getInstance().getLoginActual().addListener((observable, oldValue, newValue) -> {
			actualizarImgLogin(newValue);
		});

		timelineMenu = crearAnimacionExtension(menuCliente1.prefWidthProperty(), capaMenu.opacityProperty());
		timelineMenu2 = crearAnimacionExtension(menuCliente2.prefWidthProperty(), capaMenu2.opacityProperty());
		timelineBusqueda = new Timeline();
		timelineBusqueda.getKeyFrames()
				.add(new KeyFrame(Duration.millis(0), new KeyValue(searchingLayer.opacityProperty(), 0d)));
		timelineBusqueda.getKeyFrames()
				.add(new KeyFrame(Duration.millis(150), new KeyValue(searchingLayer.opacityProperty(), 1d)));
		crearListeners();
	}

	private void crearListeners() {
		VistaManager.getInstance().getObsAnteriorCliente().addListener((observable, oldValue, newValue) -> {
			btnBack.setDisable(!newValue);
		});
		VistaManager.getInstance().getObsSiguienteCliente().addListener((observable, oldValue, newValue) -> {
			btnNext.setDisable(!newValue);
		});
		VistaManager.getInstance().getVistaActualCliente().addListener((observable, oldValue, newValue) -> {
			btnRecargar.setDisable(newValue == null);
		});
		txtBuscar.textProperty().addListener(((observable, oldValue, newValue) -> {
			if (newValue == null || newValue.isBlank()) {
				btnSearch.setText("Buscar"); // TODO property
				btnSearch.setTextFill(azulClaro);
			} else {
				btnSearch.setText(newValue);
				btnSearch.setTextFill(azulOscuro);
			}
		}));
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

	private void searchBtnAction() {
		searchingLayer.setDisable(isBusquedaShown);
		if (isBusquedaShown) {
			timelineBusqueda.stop();
			timelineBusqueda.setRate(-1);
			timelineBusqueda.jumpTo(Duration.millis(150));
			timelineBusqueda.play();
		} else {
			timelineBusqueda.setOnFinished((e -> txtBuscar.requestFocus()));
			timelineBusqueda.playFromStart();
		}
		isBusquedaShown = !isBusquedaShown;
	}

	private void enterKeyActionSearch() {
		try {
			List<Paquete> paquetes = new PeticionController<Void, List<Paquete>>(TipoPeticion.LISTAR_PAQUETE, null)
					.realizarPeticion();
			if (paquetes.isEmpty())
				MainPaneController.getInstance().showAlert("Lista vacia"); // TODO property
			else {
				busquedaAnterior = txtBuscar.getText();
				VistaManager.getInstance().cambiarVistaCliente(TipoVista.BUSQUEDA_AVANZADA,
						new Pair<List<Paquete>, String>(paquetes, busquedaAnterior));
				searchBtnAction();
			}
		} catch (PeticionException | FXMLException e) {
			MainPaneController.getInstance().showAlert("Error:" + e.getMessage()); // TODO property
		}
	}

	private void escapeKeyActionSearch() {
		txtBuscar.setText(busquedaAnterior == null ? "" : busquedaAnterior);
		searchBtnAction();
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
				VistaManager.getInstance().cambiarVistaCliente(TipoVista.REGISTRO_GUIA, null);
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
