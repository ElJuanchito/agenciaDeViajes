package co.edu.uniquindio.agenciaviaje.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviaje.exceptions.ImagenNoObtenidaException;
import co.edu.uniquindio.agenciaviaje.model.Clima;
import co.edu.uniquindio.agenciaviaje.model.Destino;
import co.edu.uniquindio.agenciaviaje.services.Controllable;
import co.edu.uniquindio.agenciaviaje.ui.Vista;
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
	public void preInicializar() {

		Destino destino1 = Destino.builder().nombre("PokemonRojo").ciudad("Kanto").descripcion("Pokemon lagartija")
				.clima(Clima.SECO).build();

		Destino destino2 = Destino.builder().nombre("PokemonAzul").ciudad("Kanto").descripcion("Pokemon tortuga")
				.clima(Clima.FRIO).build();

		Destino destino3 = Destino.builder().nombre("PokemonVerde").ciudad("Kanto").descripcion("Pokemon plantica")
				.clima(Clima.TROPICAL).build();

		Destino destino4 = Destino.builder().nombre("eevee").ciudad("Kanto").descripcion("Pokemon chimbita")
				.clima(Clima.POLAR).build();
		Destino destino5 = Destino.builder().nombre("meltan").ciudad("Alola").descripcion("Pokemon tuerca")
				.clima(Clima.TROPICAL).build();

		Destino destino6 = Destino.builder().nombre("impidimp").ciudad("galar").descripcion("Pokemon alejo")
				.clima(Clima.POLAR).build();

		try {
			destino1.addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/charmander.png");
			destino1.addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/charmeleon.png");
			destino1.addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/charizard.png");
			destino1.addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/charizardGigamax.png");

			destino2.addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/squirtle.png");

			destino3.addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/hola3.png");

			destino4.addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/eevee.png");

			destino5.addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/meltan.png");

			destino6.addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/impidimp.png");
		} catch (ImagenNoObtenidaException e) {
			e.printStackTrace();
		}

		agregarDestino(destino1);
		agregarDestino(destino2);
		agregarDestino(destino3);
		agregarDestino(destino4);
		agregarDestino(destino5);
		agregarDestino(destino6);
	}

	public void agregarDestino(Destino destino) {
		try {
			Vista<Destino> view = Vista.buildView("destino");
			view.getController().inicializarDatos(destino);
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
