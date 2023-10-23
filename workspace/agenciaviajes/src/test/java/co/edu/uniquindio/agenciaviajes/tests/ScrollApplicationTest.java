package co.edu.uniquindio.agenciaviajes.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import co.edu.uniquindio.agenciaviajes.application.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScrollApplicationTest extends Application {

	@Test
	public void prueba() {
		launch(new String[0]);
		assertTrue(true);
	}

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("scrollTest"), 640, 480);
		stage.setScene(scene);
		stage.show();
	}

	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		return new FXMLLoader(App.class.getResource("/co/edu/uniquindio/agenciaviajes/fxml/" + fxml + ".fxml")).load();
	}
}
