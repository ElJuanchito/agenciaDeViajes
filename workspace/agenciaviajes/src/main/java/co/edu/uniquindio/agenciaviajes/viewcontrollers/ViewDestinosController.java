package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import animatefx.animation.FadeIn;
import co.edu.uniquindio.agenciaviajes.controllers.Vista;
import co.edu.uniquindio.agenciaviajes.model.Destino;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import co.edu.uniquindio.agenciaviajes.utils.DatosQuemadosAux;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ViewDestinosController implements Controllable {

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
		new Thread(this::inicializarDestinos).start();
	}

	private void inicializarDestinos() {
		destinos = null;
		MainPaneController.getInstance().ejecutarProcesoDoble(() -> {
			// aca toca que cambiar en lugar de datos quemados un llamado al data service
			destinos = DatosQuemadosAux.getInstance().getPaquete().getDestinos();
		}, () -> {
			for (Destino destino : destinos) {
				agregarDestino(destino);
			}
		});

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
		// TODO
	}

	@Override
	public void clearData() {
		// TODO
	}

}
