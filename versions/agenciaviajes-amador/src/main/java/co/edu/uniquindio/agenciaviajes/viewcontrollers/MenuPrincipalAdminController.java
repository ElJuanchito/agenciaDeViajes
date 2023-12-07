package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.controllers.DataController;
import co.edu.uniquindio.agenciaviajes.controllers.LanguageManager;
import co.edu.uniquindio.agenciaviajes.controllers.TipoVista;
import co.edu.uniquindio.agenciaviajes.controllers.VistaManager;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class MenuPrincipalAdminController implements Controllable {
	private static MenuPrincipalAdminController instance;

	public static MenuPrincipalAdminController getInstance() {
		return instance;
	}

	public MenuPrincipalAdminController() {
		instance = this;
	}

	@FXML
	private BorderPane centerPane, capaMenu, capaMenu3;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblBtnDestinos, lblbtnPaquetes, lblBtnGuias;

	@FXML
	private SVGPath btnPerfil;

	@FXML
	private ScrollPane scrollCenter;

	@FXML
	private Button btnBack, btnNext, btnExtra;

	@FXML
	private VBox menuCliente1, menuIdiomas;

	private boolean isMenuExtended, isMenu3Extended;
	private Timeline timelineMenu, timelineMenu3;

	@FXML
	void backEvent(ActionEvent event) {

	}

	@FXML
	void menuIdiomasEvent(MouseEvent event) {
		ejecutarAnimacionMenuIdiomas();
	}

	@FXML
	void perfilClickEvent(MouseEvent event) {
		ejecutarAnimacionMenuNoLogin();
	}

	@FXML
	void destinosEvent(ActionEvent event) {
		destinosAction();

	}

	@FXML
	void changeLanguageSpanish(MouseEvent event) {
		LanguageManager.getInstance().setLanguage("es");
	}

	@FXML
	void changeLanguageEnglish(MouseEvent event) {
		LanguageManager.getInstance().setLanguage(Locale.US);
	}

	@FXML
	void cerrarSesionEvent(MouseEvent event) {
		cerrarSesionAction();
	}

	@FXML
	void capaMenuClickedEvent(MouseEvent event) {
		ejecutarAnimacionMenuNoLogin();
	}

	@FXML
	void capaMenuIdiomas(MouseEvent event) {
		ejecutarAnimacionMenuIdiomas();
	}

	private void cerrarSesionAction() {
		MainPaneController.getInstance().ejecutarProceso(() -> {
			DataController.getInstance().selectUsuario(null);
			try {
				VistaManager.getInstance().cambiarVista(TipoVista.MENU_PRINCIPAL_CLIENTE, null);
			} catch (FXMLException e) {
				e.printStackTrace();
			}
		});
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
		masAction();
	}

	private void masAction() {
		MainPaneController.getInstance().ejecutarProceso(() -> {
			try {
				VistaManager.getInstance().cambiarVistaAdmin(TipoVista.ESTADISTICAS, null);
			} catch (FXMLException e) {
				e.printStackTrace();
			}
		});
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
		timelineMenu = crearAnimacionExtension(menuCliente1.prefWidthProperty(), capaMenu.opacityProperty());
		timelineMenu3 = crearAnimacionExtension(menuIdiomas.prefWidthProperty(), capaMenu3.opacityProperty());
	}

	private Timeline crearAnimacionExtension(DoubleProperty widthProperty, DoubleProperty opacityProperty) {
		Timeline timelineMenu = new Timeline();
		timelineMenu.getKeyFrames().add(
				new KeyFrame(Duration.millis(0), new KeyValue(widthProperty, 0d), new KeyValue(opacityProperty, 0d)));
		timelineMenu.getKeyFrames().add(new KeyFrame(Duration.millis(100), new KeyValue(opacityProperty, 1d),
				new KeyValue(widthProperty, 150d)));
		return timelineMenu;
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		lblBtnDestinos.setText(bundle.getString("MenuPrincipalAdminController.lblBtnDestinos"));
		lblbtnPaquetes.setText(bundle.getString("MenuPrincipalAdminController.lblbtnPaquetes"));
		lblBtnGuias.setText(bundle.getString("MenuPrincipalAdminController.lblBtnGuias"));
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

	private void ejecutarAnimacionMenuIdiomas() {
		capaMenu3.setDisable(isMenu3Extended);
		if (isMenu3Extended) {
			timelineMenu3.stop();
			timelineMenu3.setRate(-1);
			timelineMenu3.jumpTo(Duration.millis(100));
			timelineMenu3.play();
		} else {
			timelineMenu3.playFromStart();
		}
		isMenu3Extended = !isMenu3Extended;
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
}
