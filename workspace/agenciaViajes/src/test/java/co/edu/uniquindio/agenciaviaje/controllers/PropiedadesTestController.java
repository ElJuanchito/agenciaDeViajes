package co.edu.uniquindio.agenciaviaje.controllers;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviaje.i18n.LanguageManager;
import co.edu.uniquindio.agenciaviaje.services.AnimationService;
import co.edu.uniquindio.agenciaviaje.services.UtilsJPA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PropiedadesTestController implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label label1;

	@FXML
	private Label label2;

	@FXML
	private Label label3;

	@FXML
	private Button spanishBtn;

	@FXML
	private Button englishBtn;

	@FXML
	Button btnInciarSesion;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.location = location;
		this.resources = resources;
		LanguageManager.getInstance().addListener(bundle -> {
			System.out.println("hola");
			label1.setText(bundle.getString("prop1"));
			label2.setText(bundle.getString("prop2"));
			label3.setText(bundle.getString("prop3"));
			englishBtn.setText(bundle.getString("enButton"));
			spanishBtn.setText(bundle.getString("esButton"));
		});
	}

	@FXML
	void changeToEnAction(ActionEvent event) {
		LanguageManager.getInstance().setLanguage(Locale.US);
	}

	@FXML
	void changeToEsAction(ActionEvent event) {
		LanguageManager.getInstance().setLanguage("es");
	}

	@FXML
	public void iniciarSesionAction(ActionEvent event) {
		AnimationService.getInstance().ejecutarAccionBtn(btnInciarSesion, () -> {
			try {
				UtilsJPA.getEntityManager().contains(null);
				// se demora un poco al inicio, sirve para ver la animación bien
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}
}
