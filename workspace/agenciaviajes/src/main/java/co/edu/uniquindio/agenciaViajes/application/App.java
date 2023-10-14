package co.edu.uniquindio.agenciaViajes.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		Scene scene = new Scene(loadFXML("main"), 640, 480);
		stage.setScene(scene);
		stage.show();
	}

	public static Parent loadFXML(String fxml) throws Exception {
		return new FXMLLoader(App.class.getResource("/co/edu/uniquindio/agenciaviajes/fxml/" + fxml + ".fxml")).load();
	}

}