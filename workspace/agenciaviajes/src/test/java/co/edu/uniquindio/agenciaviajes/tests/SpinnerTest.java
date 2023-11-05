package co.edu.uniquindio.agenciaviajes.tests;

import org.junit.Test;

import co.edu.uniquindio.agenciaviajes.ui.TipoVista;
import co.edu.uniquindio.agenciaviajes.ui.Vista;
import co.edu.uniquindio.agenciaviajes.ui.VistaManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SpinnerTest extends Application {
	
	private Scene scene;
	
	@Test
	public  void pruebaSpinner() {
		launch();

	}

	@Override
	public void start(Stage stage) throws Exception {
		scene = new Scene(Vista.buildView("crearReserva").getParent());
		stage.setScene(scene);
		stage.show();
		
	}

}
