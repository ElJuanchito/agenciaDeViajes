package co.edu.uniquindio.agenciaViajes.services;

import co.edu.uniquindio.agenciaViajes.i18n.LanguageManager;
import co.edu.uniquindio.agenciaViajes.ui.Vista;
import javafx.application.Platform;
import javafx.scene.control.Button;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnimationService {
	private static AnimationService instance;

	public static AnimationService getInstance() {
		if (instance == null)
			instance = new AnimationService();
		return instance;
	}

	public void ejecutarAccionBtn(Button button, Runnable runnable) {
		if (button.getGraphic() == null) {
			String originalText = button.getText();
			try {
				button.setGraphic(Vista.buildView("loadingBtn").getParent());
				button.setText("");
				button.setDisable(true);
			} catch (Exception e) {
				// en caso de emergencia
				button.setText(LanguageManager.getInstance().getString("loadingState"));
				button.setDisable(true);
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
