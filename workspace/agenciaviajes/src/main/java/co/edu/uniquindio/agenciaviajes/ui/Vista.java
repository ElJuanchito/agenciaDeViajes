package co.edu.uniquindio.agenciaviajes.ui;

import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Vista<T> {
	private DataControllable<T> controller;
	private Parent parent;

	public static <Y> Vista<Y> buildView(String fxml) throws FXMLException {
		FXMLLoader fxmlLoader = new FXMLLoader(
				Vista.class.getResource("/co/edu/uniquindio/agenciaviajes/fxml/" + fxml + ".fxml"));
		Parent parent;
		try {
			parent = fxmlLoader.load();
		} catch (Exception e) {
			throw new FXMLException("No se pudo cargar el fxml:'" + fxml + "'", e);
		}
		DataControllable<Y> controller = fxmlLoader.getController();
		return new Vista<Y>(controller, parent);
	}

	public void clearData() {
		controller.clearData();
	}

	public void inicializarDatos(T dato) {
		controller.inicializarDatos(dato);
	}

	public void updateLanguage(ResourceBundle resourceBundle) {
		controller.updateLanguage(resourceBundle);
	}
}
