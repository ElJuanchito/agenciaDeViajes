package co.edu.uniquindio.agenciaViajes.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaViajes.model.Destino;
import co.edu.uniquindio.agenciaViajes.services.DataControllable;
import co.edu.uniquindio.agenciaViajes.utils.UtilsFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import lombok.Getter;

public class DestinoController implements DataControllable<Destino> {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnNext;

	@FXML
	private Button btnPrevious;

	@FXML
	private ImageView imgDestino;

	@FXML
	private Label lblCity;

	@FXML
	private Label lblDescription;

	@FXML
	private Label lblName;

	@FXML
	private Label lblWeather;

	@FXML
	private VBox mainPane;

	@FXML
	private Label txtCity;

	@FXML
	private Label txtDescription;

	@FXML
	private Label txtName;

	@FXML
	private Label txtWeather;

	@Getter
	private Destino destino;

	private List<Image> listaImagenes = new ArrayList<Image>();

	private int currentIndex = 0;

	@FXML
	void nextEvent(ActionEvent event) {
		nextAction();
	}

	@FXML
	void previousEvent(ActionEvent event) {
		previousAction();
	}

	private void previousAction() {
		showPreviousImage();
	}

	private void nextAction() {
		showNextImage();

	}

	private void showActualImage() {
		if (currentIndex >= 0 && currentIndex < listaImagenes.size()) {
			Image imagen = listaImagenes.get(currentIndex);
			imgDestino.setImage(imagen);
		}
	}

	private void showPreviousImage() {
		if (currentIndex > 0) {
			currentIndex--;
			showActualImage();
		}
	}

	private void showNextImage() {
		if (currentIndex < listaImagenes.size() - 1) {
			currentIndex++;
			showActualImage();
		}
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
	}

	@Override
	public void clearData() {
		txtName.setText("");
		txtCity.setText("");
		txtDescription.setText("");
		txtWeather.setText("");
		listaImagenes.clear();
		showActualImage();
		currentIndex = 0;
		this.destino = null;
	}

	@Override
	public void inicializarDatos(Destino dato) {
		this.destino = dato;
		if (destino == null) {
			clearData();
			return;
		}
		txtName.setText(destino.getNombre());
		txtCity.setText(destino.getCiudad());
		txtDescription.setText(destino.getDescripcion());
		txtWeather.setText(destino.getClima().getText());
		listaImagenes = UtilsFX.cargarImagenes(destino.getImagenes());
		showActualImage();
	}

	@Override
	public void preInicializar() {
	}

}
