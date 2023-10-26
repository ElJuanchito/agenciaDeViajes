package co.edu.uniquindio.agenciaviajes.application;

import co.edu.uniquindio.agenciaviajes.ui.TipoVista;
import co.edu.uniquindio.agenciaviajes.ui.VistaManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
//		scene = new Scene(new BorderPane());
//		stage.setScene(scene);
//		VistaManager.getInstance().cambiarVista(TipoVista.LOGIN, null);
		Image icon = new Image(getClass().getResourceAsStream("/co/edu/uniquindio/agenciaviajes/imagenes/login.png"));
//		stage.setTitle("PokeViajes");
//		stage.heightProperty().addListener((obser, oldV, newV) -> stage.setMinWidth((Double) newV * 1.3));
//		stage.setMinHeight(380);
//		stage.centerOnScreen();
		scene = new Scene(new FXMLLoader(App.class.getResource("/co/edu/uniquindio/agenciaviajes/fxml/registroGuia.fxml")).load());
		stage.setScene(scene);
		stage.getIcons().add(icon);
		stage.show();
	}

	public static void setRoot(Parent value) {
		scene.setRoot(value);
	}

}