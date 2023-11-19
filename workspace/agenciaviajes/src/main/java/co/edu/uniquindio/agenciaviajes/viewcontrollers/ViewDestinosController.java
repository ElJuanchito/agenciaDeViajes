package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import animatefx.animation.FadeIn;
import co.edu.uniquindio.agenciaviajes.controllers.Vista;
import co.edu.uniquindio.agenciaviajes.model.Destino;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ViewDestinosController implements DataControllable<List<Destino>> {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private GridPane contentPane;

	@FXML
	private Label lblTitle;

	@FXML
	private VBox mainPane;

	private int rowIndex = 0;
	private int colIndex = 0;

	private List<Destino> destinos;

	@Override
	public void preInicializar() {
	}

	public void agregarDestino(Destino destino) {
		try {
			Vista<Destino> view = Vista.buildView("destino");
			view.getController().inicializarDatos(destino);
			Platform.runLater(() -> cargarDestinoVista(view.getParent()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarDestinoVista(Parent parent) {
		FadeIn fadeIn = new FadeIn(parent);
		contentPane.add(parent, colIndex, rowIndex);
		fadeIn.play();
		colIndex = 1 - colIndex;
		if (colIndex == 0)
			rowIndex++;
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		lblTitle.setText(bundle.getString("ViewDestinosController.lblTitle"));
	}

	@Override
	public void clearData() {
	}

	@Override
	public void inicializarDatos(List<Destino> dato) {
		new Thread(() -> {
			Platform.runLater(() -> {
				contentPane.getChildren().clear();
				rowIndex = 0;
				colIndex = 0;
				for (Destino destino : destinos) {
					agregarDestino(destino);
				}
			});
		}).start();
	}

}
