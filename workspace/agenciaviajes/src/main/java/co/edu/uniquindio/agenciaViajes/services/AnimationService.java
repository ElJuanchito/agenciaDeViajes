package co.edu.uniquindio.agenciaViajes.services;

import co.edu.uniquindio.agenciaViajes.application.App;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;

public class AnimationService {
	private static AnimationService instance;

	public static AnimationService getInstance() {
		if (instance == null)
			instance = new AnimationService();
		return instance;
	}

	private AnimationService() {
	}

	public void ejecutarAccionBtn(Button button, Runnable runnable) {
		if (button.getGraphic() == null) {
			String originalText = button.getText();
			try {
				button.setGraphic(App.loadFXML("loadingBtn"));
				button.setText("");
				button.setDisable(true);
			} catch (Exception e) {
				button.setText("Cargando...");
			}
			new Thread(() -> {
				runnable.run();
				Platform.runLater(() -> {
					button.setText(originalText);
					button.setGraphic(null);
					button.setDisable(false);
				});
			}).start();
		}
	}
}
