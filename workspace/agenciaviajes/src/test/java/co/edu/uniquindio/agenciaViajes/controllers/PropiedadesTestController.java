package co.edu.uniquindio.agenciaViajes.controllers;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaViajes.services.Propiedades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.location = location;
		this.resources = resources;
		Propiedades.getInstance().addListener(bundle -> {
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
		Propiedades.getInstance().setLanguage(Locale.US);
	}

	@FXML
	void changeToEsAction(ActionEvent event) {
		Propiedades.getInstance().setLanguage("es");
	}
}
