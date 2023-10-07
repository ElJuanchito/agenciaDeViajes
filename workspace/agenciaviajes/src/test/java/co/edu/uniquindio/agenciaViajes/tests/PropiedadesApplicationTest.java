package co.edu.uniquindio.agenciaViajes.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ResourceBundle;

import org.junit.Test;

import co.edu.uniquindio.agenciaViajes.application.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PropiedadesApplicationTest extends Application {

	@Test
	public void prueba() {
		launch(new String[0]);
		assertTrue(true);
	}

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("propertiesTest"), 640, 480);
		stage.setScene(scene);
		stage.show();
	}

	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		return new FXMLLoader(App.class.getResource("/co/edu/uniquindio/agenciaviajes/fxml/" + fxml + ".fxml")).load();
	}

	@FunctionalInterface
	public static interface Traducible {
		public void actualizarBundle(ResourceBundle bundle);
	}
}
