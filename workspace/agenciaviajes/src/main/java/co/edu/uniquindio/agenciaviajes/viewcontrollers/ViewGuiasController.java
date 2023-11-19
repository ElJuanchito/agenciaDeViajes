package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import animatefx.animation.FadeIn;
import co.edu.uniquindio.agenciaviajes.controllers.Vista;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.model.GuiaTuristico;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import co.edu.uniquindio.agenciaviajes.utils.DatosQuemadosAux;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ViewGuiasController implements Controllable {

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

	private List<GuiaTuristico> guias;

	@Override
	public void preInicializar() {
		new Thread(this::inicializarGuias).start();
	}

	private void inicializarGuias() {
		guias = null;
		MainPaneController.getInstance().ejecutarProcesoDoble(() -> {

			guias = DatosQuemadosAux.getInstance().obtenerListaGuias();
		}, () -> {
			for (GuiaTuristico paquete : guias) {
				agregarGuias(paquete);
			}
		});

	}

	private void agregarGuias(GuiaTuristico guia) {
		// Falta crear la ventana de paquete aunque no se si modificar la misma de
		// destino
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
		// TODO Auto-generated method stub

	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub

	}

	// Aun no voy a hacer lo de realizar peticiones, primero voy a tratar de probar
	// que la ventana funcione

}
