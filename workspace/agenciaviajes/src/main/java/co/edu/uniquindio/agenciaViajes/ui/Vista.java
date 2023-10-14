package co.edu.uniquindio.agenciaViajes.ui;

import co.edu.uniquindio.agenciaViajes.application.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Vista {
	private Object controller;
	private Parent parent;

	public static Vista buildView(String fxml) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(
				App.class.getResource("/co/edu/uniquindio/agenciaviajes/fxml/" + fxml + ".fxml"));
		Parent parent = fxmlLoader.load();
		Object controller = fxmlLoader.getController();
		return new Vista(controller, parent);
	}
}
