package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import animatefx.animation.FadeIn;
import co.edu.uniquindio.agenciaviajes.controllers.Vista;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.model.GuiaTuristico;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ViewGuiasController implements DataControllable<List<GuiaTuristico>> {

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

	@Override
	public void preInicializar() {
	}

	private void agregarGuias(GuiaTuristico guia) {
		try {
			Vista<GuiaTuristico> view = Vista.buildView("guiaTuristico");
			view.getController().inicializarDatos(guia);
			Platform.runLater(() -> cargarGuiasVista(view.getParent()));
		} catch (FXMLException e) {
			throw new RuntimeException(e);
		}

	}

	private void cargarGuiasVista(Parent parent) {
		FadeIn fadeIn = new FadeIn(parent);
		contentPane.add(parent, colIndex, rowIndex);
		fadeIn.play();
		colIndex = 1 - colIndex;
		if (colIndex == 0)
			rowIndex++;
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		// TODO titulo

	}

	@Override
	public void clearData() {
	}

	@Override
	public void inicializarDatos(List<GuiaTuristico> dato) {
		new Thread(() -> {
			Platform.runLater(() -> {
				contentPane.getChildren().clear();
				colIndex = 0;
				rowIndex = 0;
				for (GuiaTuristico paquete : dato) {
					agregarGuias(paquete);
				}
			});
		}).start();
	}
}
