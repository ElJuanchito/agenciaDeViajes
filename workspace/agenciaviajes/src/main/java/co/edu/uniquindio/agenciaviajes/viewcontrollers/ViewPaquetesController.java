package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import animatefx.animation.FadeIn;
import co.edu.uniquindio.agenciaviajes.controllers.Vista;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.model.Paquete;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ViewPaquetesController implements DataControllable<List<Paquete>> {

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

	private List<Paquete> paquetes;

	@Override
	public void preInicializar() {
	}

	private void agregarPaquetes(Paquete paquete) {
		try {
			Vista<Paquete> view = Vista.buildView("paquete");
			view.getController().inicializarDatos(paquete);
			Platform.runLater(() -> cargarDestinoVista(view.getParent()));
		} catch (FXMLException e) {
			throw new RuntimeException(e);
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
		lblTitle.setText(bundle.getString("ViewPaquetesController.lblTitle"));
	}

	@Override
	public void clearData() {
	}

	@Override
	public void inicializarDatos(List<Paquete> dato) {
		this.paquetes = dato == null ? new ArrayList<Paquete>() : dato;
		new Thread(() -> {
			Platform.runLater(() -> {
				colIndex = 0;
				rowIndex = 0;
				contentPane.getChildren().clear();
				for (Paquete paquete : paquetes) {
					agregarPaquetes(paquete);
				}
			});

		}).start();
	}

}
