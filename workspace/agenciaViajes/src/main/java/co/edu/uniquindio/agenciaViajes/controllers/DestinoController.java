package co.edu.uniquindio.agenciaViajes.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaViajes.model.Destino;
import co.edu.uniquindio.agenciaViajes.utils.UtilsFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DestinoController implements Initializable {

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

	private List<Image> listaImagenes;

	private int currentIndex;

	/**
	 * 
	 * @author ElJuancho
	 */
	@Builder
	public DestinoController(Destino destino) {
		this.destino = destino;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

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

	public void setDestino(Destino destino) {
		this.destino = destino;
		txtName.setText(destino.getNombre());
		txtCity.setText(destino.getCiudad());
		txtDescription.setText(destino.getDescripcion());
		txtWeather.setText(destino.getClima().getText());
		listaImagenes = UtilsFX.cargarImagenes(destino.getImagenes());
		showActualImage();
	}

}
