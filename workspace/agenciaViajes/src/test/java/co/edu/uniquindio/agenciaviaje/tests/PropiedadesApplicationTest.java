package co.edu.uniquindio.agenciaviaje.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import co.edu.uniquindio.agenciaviaje.ui.Vista;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PropiedadesApplicationTest extends Application {

	@Test
	public void prueba() {
		launch(new String[0]);
		assertTrue(true);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(Vista.buildView("propertiesTest").getParent(), 640, 480);
		stage.setScene(scene);
		stage.show();
	}

}
