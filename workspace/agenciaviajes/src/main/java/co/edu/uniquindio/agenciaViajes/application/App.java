package co.edu.uniquindio.agenciaViajes.application;

import java.io.IOException;

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

	private static Scene scene;

	@Override
	public void start(Stage stage) throws Exception {
		scene = new Scene(loadFXML("main"), 640, 480);
		stage.setScene(scene);
		stage.show();
	}

	static void setRoot(String fxml) throws Exception {
		scene.setRoot(loadFXML(fxml));
	}

	public static Parent loadFXML(String fxml) throws Exception {
		return new FXMLLoader(App.class.getResource("/co/edu/uniquindio/agenciaviajes/fxml/" + fxml + ".fxml")).load();
	}

}