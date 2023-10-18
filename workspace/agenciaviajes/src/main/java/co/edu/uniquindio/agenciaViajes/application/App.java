package co.edu.uniquindio.agenciaViajes.application;

import co.edu.uniquindio.agenciaViajes.ui.TipoVista;
import co.edu.uniquindio.agenciaViajes.ui.VistaManager;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
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
		scene = new Scene(new BorderPane());
		stage.setScene(scene);
		VistaManager.getInstance().cambiarVista(TipoVista.LOGIN, null);
		Image icon = new Image(getClass().getResourceAsStream("/co/edu/uniquindio/agenciaviajes/imagenes/login.png"));
		stage.setTitle("PokeViajes");
		stage.getIcons().add(icon);
		stage.show();
	}

	public static void setRoot(Parent value) {
		scene.setRoot(value);
	}

}