package co.edu.uniquindio.agenciaViajes.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaViajes.exceptions.ImagenYaExistenteException;
import co.edu.uniquindio.agenciaViajes.model.Clima;
import co.edu.uniquindio.agenciaViajes.model.Destino;
import co.edu.uniquindio.agenciaViajes.services.Controllable;
import co.edu.uniquindio.agenciaViajes.services.DataControllable;
import co.edu.uniquindio.agenciaViajes.ui.Vista;
import javafx.fxml.FXML;
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

	@Override
	public void inicializarVentana() {

		Destino destino1 = Destino.builder().nombre("PokemonRojo").ciudad("Kanto").descripcion("Pokemon lagartija")
				.clima(Clima.SECO).build();
		try {
			destino1.addImagen(ViewDestinosController.class
					.getResource("/co/edu/uniquindio/agenciaviajes/imagenes/charmander.png").toExternalForm());
			destino1.addImagen(ViewDestinosController.class
					.getResource("/co/edu/uniquindio/agenciaviajes/imagenes/charmeleon.png").toExternalForm());
			destino1.addImagen(ViewDestinosController.class
					.getResource("/co/edu/uniquindio/agenciaviajes/imagenes/charizard.png").toExternalForm());
			destino1.addImagen(ViewDestinosController.class
					.getResource("/co/edu/uniquindio/agenciaviajes/imagenes/charizardGigamax.png").toExternalForm());
		} catch (ImagenYaExistenteException e) {
			e.printStackTrace();
		}

		Destino destino2 = Destino.builder().nombre("PokemonAzul").ciudad("Kanto").descripcion("Pokemon tortuga")
				.clima(Clima.FRIO).build();
		try {
			destino2.addImagen(ViewDestinosController.class
					.getResource("/co/edu/uniquindio/agenciaviajes/imagenes/squirtle.png").toExternalForm());
		} catch (ImagenYaExistenteException e) {
			e.printStackTrace();
		}

		Destino destino3 = Destino.builder().nombre("PokemonVerde").ciudad("Kanto").descripcion("Pokemon plantica")
				.clima(Clima.TROPICAL).build();
		try {
			destino3.addImagen(ViewDestinosController.class
					.getResource("/co/edu/uniquindio/agenciaviajes/imagenes/bulbasaur.png").toExternalForm());
		} catch (ImagenYaExistenteException e) {
			e.printStackTrace();
		}

		Destino destino4 = Destino.builder().nombre("eevee").ciudad("Kanto").descripcion("Pokemon chimbita")
				.clima(Clima.POLAR).build();
		try {
			destino4.addImagen(ViewDestinosController.class
					.getResource("/co/edu/uniquindio/agenciaviajes/imagenes/eevee.png").toExternalForm());
		} catch (ImagenYaExistenteException e) {
			e.printStackTrace();
		}

		Destino destino5 = Destino.builder().nombre("meltan").ciudad("Alola").descripcion("Pokemon tuerca")
				.clima(Clima.TROPICAL).build();
		try {
			destino5.addImagen(ViewDestinosController.class
					.getResource("/co/edu/uniquindio/agenciaviajes/imagenes/meltan.png").toExternalForm());
		} catch (ImagenYaExistenteException e) {
			e.printStackTrace();
		}

		Destino destino6 = Destino.builder().nombre("impidimp").ciudad("galar").descripcion("Pokemon alejo")
				.clima(Clima.POLAR).build();
		try {
			destino6.addImagen(ViewDestinosController.class
					.getResource("/co/edu/uniquindio/agenciaviajes/imagenes/impidimp.png").toExternalForm());
		} catch (ImagenYaExistenteException e) {
			e.printStackTrace();
		}

		agregarDestino(destino1);
		agregarDestino(destino2);
		agregarDestino(destino3);
		agregarDestino(destino4);
		agregarDestino(destino5);
		agregarDestino(destino6);
	}

	@SuppressWarnings("unchecked")
	public void agregarDestino(Destino destino) {
		try {
			Vista view = Vista.buildView("destino");
			((DataControllable<Destino>) view.getController()).inicializarDatos(destino);
			contentPane.add(view.getParent(), colIndex, rowIndex);

			colIndex = 1 - colIndex;
			if (colIndex == 0)
				rowIndex++;

		} catch (Exception e) {
			e.printStackTrace();
		}
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
