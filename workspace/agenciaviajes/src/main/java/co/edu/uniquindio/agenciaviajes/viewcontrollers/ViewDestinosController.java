package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import animatefx.animation.FadeIn;
import co.edu.uniquindio.agenciaviajes.controllers.PeticionController;
import co.edu.uniquindio.agenciaviajes.controllers.TipoPeticion;
import co.edu.uniquindio.agenciaviajes.controllers.Vista;
import co.edu.uniquindio.agenciaviajes.exceptions.PeticionException;
import co.edu.uniquindio.agenciaviajes.model.Destino;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
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
		reiniciarDestinos();
	}

	private void reiniciarDestinos() {
		new Thread(this::inicializarDestinos).start();
	}

	private void inicializarDestinos() {
		destinos = null;
		MainPaneController.getInstance().ejecutarProcesoDoble(() -> {
			// aca toca que cambiar en lugar de datos quemados un llamado al data service
			realizarPeticionDestinos();
		}, () -> {
			for (Destino destino : destinos) {
				agregarDestino(destino);
			}
		});

	}

	private void realizarPeticionDestinos() {
		try {
			destinos = new PeticionController<Void, List<Destino>>(TipoPeticion.LISTAR_DESTINO, null)
					.realizarPeticion();
		} catch (PeticionException e) {
			destinos = new ArrayList<Destino>();
			MainPaneController.getInstance().showAlert("No se pudo realizar la peticion" + e.getMessage());
		}
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
		reiniciarDestinos();
	}

}
