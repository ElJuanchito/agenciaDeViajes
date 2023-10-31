package co.edu.uniquindio.agenciaviajes.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.model.Destino;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
import co.edu.uniquindio.agenciaviajes.utils.UtilsFX;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Duration;
import lombok.Getter;

public class DestinoController implements DataControllable<Destino> {

	@FXML
	private ResourceBundle resources;

	private static final String starPath = "M11.2691 4.41115C11.5006 3.89177 11.6164 3.63208 11.7776 3.55211C11.9176 3.48263 12.082 3.48263 12.222 3.55211C12.3832 3.63208 12.499 3.89177 12.7305 4.41115L14.5745 8.54808C14.643 8.70162 14.6772 8.77839 14.7302 8.83718C14.777 8.8892 14.8343 8.93081 14.8982 8.95929C14.9705 8.99149 15.0541 9.00031 15.2213 9.01795L19.7256 9.49336C20.2911 9.55304 20.5738 9.58288 20.6997 9.71147C20.809 9.82316 20.8598 9.97956 20.837 10.1342C20.8108 10.3122 20.5996 10.5025 20.1772 10.8832L16.8125 13.9154C16.6877 14.0279 16.6252 14.0842 16.5857 14.1527C16.5507 14.2134 16.5288 14.2807 16.5215 14.3503C16.5132 14.429 16.5306 14.5112 16.5655 14.6757L17.5053 19.1064C17.6233 19.6627 17.6823 19.9408 17.5989 20.1002C17.5264 20.2388 17.3934 20.3354 17.2393 20.3615C17.0619 20.3915 16.8156 20.2495 16.323 19.9654L12.3995 17.7024C12.2539 17.6184 12.1811 17.5765 12.1037 17.56C12.0352 17.5455 11.9644 17.5455 11.8959 17.56C11.8185 17.5765 11.7457 17.6184 11.6001 17.7024L7.67662 19.9654C7.18404 20.2495 6.93775 20.3915 6.76034 20.3615C6.60623 20.3354 6.47319 20.2388 6.40075 20.1002C6.31736 19.9408 6.37635 19.6627 6.49434 19.1064L7.4341 14.6757C7.46898 14.5112 7.48642 14.429 7.47814 14.3503C7.47081 14.2807 7.44894 14.2134 7.41394 14.1527C7.37439 14.0842 7.31195 14.0279 7.18708 13.9154L3.82246 10.8832C3.40005 10.5025 3.18884 10.3122 3.16258 10.1342C3.13978 9.97956 3.19059 9.82316 3.29993 9.71147C3.42581 9.58288 3.70856 9.55304 4.27406 9.49336L8.77835 9.01795C8.94553 9.00031 9.02911 8.99149 9.10139 8.95929C9.16534 8.93081 9.2226 8.8892 9.26946 8.83718C9.32241 8.77839 9.35663 8.70162 9.42508 8.54808L11.2691 4.41115Z";

	@FXML
	private URL location;

	@FXML
	private Button btnNext, btnPrevious;

	@FXML
	private ImageView imgDestino, imgDestino2;

	@FXML
	private Label txtDescription, txtName, txtPuntuacion;

	@FXML
	private HBox boxStars;

	@Getter
	private Destino destino;

	private List<Image> listaImagenes = new ArrayList<Image>();

	private int currentIndex = 0;

	private Timeline timelinePt1;

	private ParallelTransition timelineHover;

	@FXML
	private SVGPath stars[];

	private Color starFill;

	@FXML
	void nextEvent(ActionEvent event) {
		nextAction();
	}

	@FXML
	void previousEvent(ActionEvent event) {
		previousAction();
	}

	@FXML
	void hoverPanelEvent(MouseEvent event) {
		hoverPanelAction();
	}

	@FXML
	void unhoverPanelEvent(MouseEvent event) {
		unhoverPanelAction();
	}

	private void hoverPanelAction() {
		timelineHover.playFromStart();
	}

	private void unhoverPanelAction() {
		timelineHover.stop();
		timelineHover.setRate(-1);
		timelineHover.jumpTo(Duration.millis(100));
		timelineHover.play();
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

			KeyFrame keyFrame = new KeyFrame(Duration.millis(0), new KeyValue(imgDestino.xProperty(), xSmallPos));
			KeyFrame keyFrame2 = new KeyFrame(Duration.millis(100), new KeyValue(imgDestino.xProperty(), xBigPos));
			timelineHover = new ParallelTransition(new Timeline(keyFrame, keyFrame2), timelinePt1);
		}
	}

	private void showPreviousImage() {
		currentIndex--;
		timelinePt1.setRate(1);
		if (currentIndex < 0)
			currentIndex = listaImagenes.size() - 1;
		showActualImage();
	}

	private void showNextImage() {
		currentIndex++;
		timelinePt1.setRate(1);
		if (currentIndex >= listaImagenes.size())
			currentIndex = 0;
		showActualImage();
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
	}

	@Override
	public void clearData() {
		txtName.setText("");
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
		listaImagenes = UtilsFX.cargarImagenes(destino.getImagenes());
		actualizarPuntaje(dato.getPromedio());
		showActualImage();
	}

	private void actualizarPuntaje(double puntuacion) {
		txtPuntuacion.setText(String.valueOf(puntuacion));

		for (int i = 0; i < stars.length; i++) {
			SVGPath star = stars[i];
			double value = puntuacion - i;
			if (value >= 1)
				star.setFill(starFill);
			else if (value > 0)
				star.setFill(new LinearGradient(value, 1.0, value + 0.001, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(0.0, starFill), new Stop(1.0, Color.TRANSPARENT)));
			else
				star.setFill(Color.TRANSPARENT);
		}
	}

	@Override
	public void preInicializar() {
		KeyFrame keyFrame = new KeyFrame(Duration.millis(0), new KeyValue(imgDestino.fitHeightProperty(), 220));
		KeyFrame keyFrame2 = new KeyFrame(Duration.millis(100), new KeyValue(imgDestino.fitHeightProperty(), 280));
		timelineHover = new ParallelTransition();
		starFill = new Color(0.1529, 0.0196, 0.4392, 1.0);

		timelinePt1 = new Timeline(keyFrame, keyFrame2);
		timelinePt1.setRate(-1);
		stars = new SVGPath[5];
		for (int i = 0; i < stars.length; i++) {
			stars[i] = new SVGPath();
			stars[i].setFillRule(FillRule.EVEN_ODD);
			stars[i].setStrokeLineCap(StrokeLineCap.ROUND);
			stars[i].setStrokeWidth(2);
			stars[i].setScaleX(1.5);
			stars[i].setScaleY(1.5);
			stars[i].setContent(starPath);
			stars[i].setStroke(starFill);
			boxStars.getChildren().add(stars[i]);
		}
	}

}
