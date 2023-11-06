package co.edu.uniquindio.agenciaviajes.controllers;

import java.util.List;
import java.util.ResourceBundle;

import animatefx.animation.AnimationFX;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideInRight;
import animatefx.animation.SlideOutLeft;
import animatefx.animation.SlideOutRight;
import animatefx.util.ParallelAnimationFX;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.model.Destino;
import co.edu.uniquindio.agenciaviajes.model.Paquete;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
import co.edu.uniquindio.agenciaviajes.ui.TipoVista;
import co.edu.uniquindio.agenciaviajes.ui.Vista;
import co.edu.uniquindio.agenciaviajes.ui.VistaManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PaqueteDetailsController implements DataControllable<Paquete> {

	@FXML
	private HBox boxDestinos;

	@FXML
	private Button btnBackDestinos;

	@FXML
	private Button btnNextDestinos;

	@FXML
	private Button btnReservar;

	@FXML
	private Label lblDestinos;

	@FXML
	private Label lblFechaFinal;

	@FXML
	private Label lblFechaInicial;

	@FXML
	private Label lblInfoCupos;

	@FXML
	private Label lblInfoDescripcion;

	@FXML
	private Label lblInfoFechaFinal;

	@FXML
	private Label lblInfoFechaInicial;

	@FXML
	private Label lblInfoPaquete;

	@FXML
	private Label lblInfoPrecio;

	@FXML
	private Label lblInfoServiciosExtra;

	@FXML
	private Label lblPaquete;

	@FXML
	private Label lblPrecio;

	@FXML
	private Label lblServiciosExtra;

	@FXML
	private VBox mainPane;

	@FXML
	private StackPane stackDestino;

	private Vista<Destino> vistaDestino1;

	private Vista<Destino> vistaDestino2;

	private boolean isFirstShowing = false;

	private int actualIndex = 0;

	private List<Destino> destinos;

	private Circle[] arrCirculos;

	private Color colorCirculos;

	private Paquete paquete;

	@FXML
	void backDestinosEvent(ActionEvent event) {
		backDestinosAction();
	}

	@FXML
	void nextDestinosEvent(ActionEvent event) {
		nextDestinosAction();
	}

	@FXML
	void pasarReservarEvent(ActionEvent event) {
		pasarReservaAction();
	}

	@Override
	public void preInicializar() {
		colorCirculos = new Color(0.1529, 0.0196, 0.4392, 1.0);

	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inicializarDatos(Paquete paquete) {
		this.paquete = paquete;
		if (paquete == null)
			return;

		lblInfoPaquete.setText(paquete.getNombre());
		lblInfoCupos.setText(String.format("¡Quedan %d cupos!", paquete.getCupoMaximo()));
		lblInfoPrecio.setText(paquete.getPrecio().toString());
		lblInfoDescripcion.setText(paquete.getDescripcion());
		lblInfoServiciosExtra.setText(paquete.getServiciosAdicionales());

		boxDestinos.getChildren().clear();
		stackDestino.getChildren().clear();
		destinos = paquete.getDestinos();
		if (destinos.isEmpty()) {
			Label lblNoDestinos = new Label("Este paquete no tiene destinos");
			lblNoDestinos.setMaxWidth(Double.MAX_VALUE);
			lblNoDestinos.setWrapText(true);
			stackDestino.getChildren().add(lblNoDestinos);
		}
		try {
			vistaDestino1 = Vista.buildView("destino");
			vistaDestino2 = Vista.buildView("destino");
			vistaDestino1.cargarIdioma();
			vistaDestino2.cargarIdioma();
			vistaDestino2.cargarDato(destinos.get(0));
			stackDestino.getChildren().add(vistaDestino1.getParent());
			stackDestino.getChildren().add(vistaDestino2.getParent());
			arrCirculos = new Circle[destinos.size()];
			for (int i = 0; i < arrCirculos.length; i++) {
				arrCirculos[i] = new Circle(5);
				arrCirculos[i].setStroke(colorCirculos);
				arrCirculos[i].setStrokeWidth(1);
				arrCirculos[i].setFill(Color.WHITE);
				final int index = i;
				arrCirculos[i].setOnMouseClicked(e -> moverImagen(index));
				if (i == actualIndex) {
					arrCirculos[i].setFill(colorCirculos);
				}
				boxDestinos.getChildren().add(arrCirculos[i]);
			}
		} catch (FXMLException e) {
			throw new RuntimeException(e);
		}

	}

	private void pasarReservaAction() {
		MainPaneController.getInstance().showAlert("Validacion de cliente");
		MainPaneController.getInstance().ejecutarProceso(() -> {
			try {
				VistaManager.getInstance().cambiarVistaCliente(TipoVista.CREAR_RESERVA, paquete);
			} catch (FXMLException e) {
				throw new RuntimeException(e);
			}
		});
	}

	private void backDestinosAction() {
		int size = destinos.size();
		if (destinos.isEmpty() || size == 1)
			return;
		arrCirculos[actualIndex].setFill(Color.WHITE);
		actualIndex--;
		if (actualIndex < 0)
			actualIndex = size - 1;
		arrCirculos[actualIndex].setFill(colorCirculos);
		showDestino(false);
	}

	private void showDestino(boolean derecha) {
		isFirstShowing = !isFirstShowing;
		Vista<Destino> vista = isFirstShowing ? vistaDestino1 : vistaDestino2;
		Vista<Destino> vistaOcultar = isFirstShowing ? vistaDestino2 : vistaDestino1;
		AnimationFX anim1 = derecha ? new SlideInRight(vista.getParent()) : new SlideInLeft(vista.getParent());
		FadeIn anim1Fade1 = new FadeIn(vista.getParent());
		AnimationFX anim2 = derecha ? new SlideOutLeft(vistaOcultar.getParent())
				: new SlideOutRight(vistaOcultar.getParent());
		FadeOut anim1Fade2 = new FadeOut(vistaOcultar.getParent());
		vista.cargarDato(destinos.get(actualIndex));
		new ParallelAnimationFX(anim1, anim1Fade1, anim2, anim1Fade2).play();
	}

	private void nextDestinosAction() {
		int size = destinos.size();
		if (destinos.isEmpty() || size == 1)
			return;
		arrCirculos[actualIndex].setFill(Color.WHITE);
		actualIndex++;
		if (actualIndex >= size)
			actualIndex = 0;
		arrCirculos[actualIndex].setFill(colorCirculos);
		showDestino(true);
	}

	private void moverImagen(int index) {
		if (index == actualIndex)
			return;
		arrCirculos[index].setFill(colorCirculos);
		arrCirculos[actualIndex].setFill(Color.WHITE);
		boolean derecha = index > actualIndex;
		actualIndex = index;
		showDestino(derecha);
	}

}
