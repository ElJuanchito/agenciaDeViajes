package co.edu.uniquindio.agenciaviajes.application;

import java.io.IOException;

import co.edu.uniquindio.agenciaviajes.controllers.TipoVista;
import co.edu.uniquindio.agenciaviajes.controllers.Vista;
import co.edu.uniquindio.agenciaviajes.controllers.VistaManager;
import co.edu.uniquindio.agenciaviajes.viewcontrollers.MainPaneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		scene = new Scene(Vista.buildView("mainPane").getParent());
		stage.setScene(scene);
		VistaManager.getInstance().cambiarVista(TipoVista.MENU_PRINCIPAL_CLIENTE, null);
		VistaManager.getInstance().cambiarVistaCliente(TipoVista.NONE, null);
		Image icon = new Image(
				getClass().getResourceAsStream("/co/edu/uniquindio/agenciaviajes/imagenes/pikachuViajero.png"));
		stage.setTitle("PokeViajes");
		stage.heightProperty().addListener((obser, oldV, newV) -> stage.setMinWidth((Double) newV * 1.3));
		stage.setMinHeight(380);
		stage.centerOnScreen();
		stage.getIcons().add(icon);
		stage.show();
	}

	public static void setRoot(Parent value) {
		MainPaneController.getInstance().setRoot(value);
	}

	public static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/co/edu/uniquindio/fxml/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

}