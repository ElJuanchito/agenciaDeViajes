package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.model.Destino;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
import co.edu.uniquindio.agenciaviajes.utils.UtilsFX;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import lombok.Getter;

public class DestinoDetailsController implements DataControllable<Destino> {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnNext, btnPrevious;

	@FXML
	private ImageView imgDestino;

	@FXML
	private Label lblCiudad, lblClima, lblDescription, lblTitle, txtCiudad, txtClima;

	@FXML
	private TextArea txtDescription;

	@Getter
	private Destino destino;

	private int currentIndex = 0;

	private List<Image> listaImagenes = new ArrayList<Image>();

	private Timeline timelinePt1;

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

			double relacionAspecto = imagen.getWidth() / imagen.getHeight();
			double xSmallPos = (310 - 220 * relacionAspecto) / 2;
			double xBigPos = (310 - 280 * relacionAspecto) / 2;

			imgDestino.setX(timelinePt1.getRate() == -1 ? xSmallPos : xBigPos);
		}
	}

	private void showPreviousImage() {
		if (currentIndex > 0) {
			currentIndex--;
			timelinePt1.setRate(1);
			showActualImage();
		}
	}

	private void showNextImage() {
		if (currentIndex < listaImagenes.size() - 1) {
			currentIndex++;
			timelinePt1.setRate(1);
			showActualImage();
		}
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		lblCiudad.setText(bundle.getString("DestinoDetailsController.lblCiudad"));
		lblClima.setText(bundle.getString("DestinoDetailsController.lblClima"));
		lblDescription.setText(bundle.getString("DestinoDetailsController.lblDescription"));
		lblTitle.setText(bundle.getString("DestinoDetailsController.lblTitle"));
	}

	@Override
	public void clearData() {
		lblTitle.setText("");
		txtCiudad.setText("");
		txtClima.setText("");
		txtDescription.setText("");
		listaImagenes.clear();
		showActualImage();
		currentIndex = 0;
		this.destino = null;
	}

	@Override
	public void inicializarDatos(Destino dato) {
		destino = dato;
		if (destino == null) {
			clearData();
			return;
		}
		lblTitle.setText(destino.getNombre());
		txtCiudad.setText(destino.getCiudad());
		txtClima.setText(destino.getClima().getText());
		txtDescription.setText(destino.getDescripcion());
		listaImagenes = UtilsFX.cargarImagenes(destino.getImagenes());
		showActualImage();

	}

	@Override
	public void preInicializar() {
		KeyFrame keyFrame = new KeyFrame(Duration.millis(0), new KeyValue(imgDestino.fitHeightProperty(), 220));
		KeyFrame keyFrame2 = new KeyFrame(Duration.millis(100), new KeyValue(imgDestino.fitHeightProperty(), 280));
		timelinePt1 = new Timeline(keyFrame, keyFrame2);
		timelinePt1.setRate(-1);
	}

}
