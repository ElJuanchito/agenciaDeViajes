package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import animatefx.animation.FadeIn;
import co.edu.uniquindio.agenciaviajes.controllers.Vista;
import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.model.Paquete;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import co.edu.uniquindio.agenciaviajes.utils.DatosQuemadosAux;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ViewPaquetesController implements Controllable {

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
		new Thread(this::inicializarPaquetes).start();
	}

	private void inicializarPaquetes() {
		paquetes = null;
		MainPaneController.getInstance().ejecutarProcesoDoble(() -> {
			paquetes = DatosQuemadosAux.getInstance().obtenerListaPaquetes();
		}, () -> {
			for (Paquete paquete : paquetes) {
				agregarPaquetes(paquete);
			}
		});

	}

	private void agregarPaquetes(Paquete paquete) {
		// Falta crear la ventana de paquete aunque no se si modificar la misma de
		// destino
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
		// TODO Auto-generated method stub

	}
}
