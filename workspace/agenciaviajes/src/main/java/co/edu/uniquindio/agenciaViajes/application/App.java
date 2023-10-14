package co.edu.uniquindio.agenciaViajes.application;

import co.edu.uniquindio.agenciaViajes.ui.Vista;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		Scene scene = new Scene(Vista.buildView("viewDestinos").getParent());
		stage.setScene(scene);
		Image icon = new Image(getClass().getResourceAsStream("/co/edu/uniquindio/agenciaviajes/imagenes/login.png"));
		stage.setTitle("PokeViajes");
		stage.getIcons().add(icon);
		stage.show();
	}

}