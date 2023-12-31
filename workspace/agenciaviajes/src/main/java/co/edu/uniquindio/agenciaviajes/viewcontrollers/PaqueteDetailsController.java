package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import animatefx.animation.AnimationFX;
import animatefx.animation.FadeInLeft;
import animatefx.animation.FadeInRight;
import animatefx.animation.FadeOutLeft;
import animatefx.animation.FadeOutRight;
import animatefx.util.ParallelAnimationFX;
import co.edu.uniquindio.agenciaviajes.controllers.TipoVista;
import co.edu.uniquindio.agenciaviajes.controllers.Vista;
import co.edu.uniquindio.agenciaviajes.controllers.VistaManager;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.model.Destino;
import co.edu.uniquindio.agenciaviajes.model.Paquete;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
import javafx.application.Platform;
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
	private Button btnBackDestinos, btnNextDestinos, btnReservar;

	@FXML
	private Label lblDestinos, lblFechaFinal, lblFechaInicial, lblInfoCupos, lblInfoDescripcion, lblInfoFechaFinal,
			lblInfoFechaInicial, lblInfoPaquete, lblInfoPrecio, lblInfoServiciosExtra, lblPaquete, lblPrecio,
			lblServiciosExtra;

	@FXML
	private VBox mainPane;

	@FXML
	private StackPane stackDestino;

	private Vista<Destino> vistaDestino1, vistaDestino2;

	private boolean isFirstShowing = false;

	private int actualIndex = 0;

	private List<Destino> destinos;

	private Circle[] arrCirculos;

	private Color colorCirculos;

	private Paquete paquete;

	private String refe;

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
		try {
			vistaDestino1 = Vista.buildView("destino");
		} catch (FXMLException e) {
			throw new RuntimeException(e);
		}
		try {
			vistaDestino2 = Vista.buildView("destino");
		} catch (FXMLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		lblPaquete.setText(bundle.getString("PaqueteDetailsController.lblPaquete"));
		
		refe = bundle.getString("PaqueteDetailsController.lblInfoCupos");
		if(paquete!=null)
			lblInfoCupos.setText(String.format(refe, paquete.getCupoMaximo()));
		
		
		btnReservar.setText(bundle.getString("PaqueteDetailsController.btnReservar"));
		lblPrecio.setText(bundle.getString("PaqueteDetailsController.lblPrecio"));
		lblServiciosExtra.setText(bundle.getString("PaqueteDetailsController.lblServiciosExtra"));
		lblDestinos.setText(bundle.getString("PaqueteDetailsController.lblDestinos"));
		lblFechaInicial.setText(bundle.getString("PaqueteDetailsController.lblFechaInicial"));
		lblFechaFinal.setText(bundle.getString("PaqueteDetailsController.lblFechaFinal"));
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
		Platform.runLater(() -> {
			lblInfoPaquete.setText(paquete.getNombre());
			lblInfoCupos.setText(String.format(refe, paquete.getCupoMaximo()));
			lblInfoPrecio.setText(paquete.getPrecio().toString());
			lblInfoDescripcion.setText(paquete.getDescripcion());
			lblInfoServiciosExtra.setText(paquete.getServiciosAdicionales());
			cargarInfoDestinos(paquete);
			DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			lblInfoFechaInicial.setText(paquete.getFechaIncio().format(pattern));
			lblInfoFechaFinal.setText(paquete.getFechaFin().format(pattern));
		});

	}

	private void cargarInfoDestinos(Paquete paquete) {
		actualIndex = 0;
		boxDestinos.getChildren().clear();
		stackDestino.getChildren().clear();
		destinos = paquete.getDestinos();
		if (destinos.isEmpty()) {
			Label lblNoDestinos = new Label("Este paquete no tiene destinos"); // TODO
			lblNoDestinos.setMaxWidth(Double.MAX_VALUE);
			lblNoDestinos.setWrapText(true);
			stackDestino.getChildren().add(lblNoDestinos);
		}
		vistaDestino1.limpiarDatos();
		vistaDestino2.limpiarDatos();
		vistaDestino1.cargarIdioma();
		vistaDestino2.cargarIdioma();
		vistaDestino2.getParent().setOpacity(1);
		vistaDestino1.getParent().setOpacity(0);
		vistaDestino1.getParent().setTranslateX(0);
		vistaDestino2.getParent().setTranslateX(0);
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
		AnimationFX animIn = derecha ? new FadeInRight(vista.getParent()) : new FadeInLeft(vista.getParent());
		AnimationFX animOut = derecha ? new FadeOutLeft(vistaOcultar.getParent())
				: new FadeOutRight(vistaOcultar.getParent());
		vista.cargarDato(destinos.get(actualIndex));
		new ParallelAnimationFX(animIn, animOut).play();
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
