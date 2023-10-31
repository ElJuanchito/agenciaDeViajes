package co.edu.uniquindio.agenciaviajes.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.exceptions.ImagenNoObtenidaException;
import co.edu.uniquindio.agenciaviajes.model.Cliente;
import co.edu.uniquindio.agenciaviajes.model.Clima;
import co.edu.uniquindio.agenciaviajes.model.Comentario;
import co.edu.uniquindio.agenciaviajes.model.Destino;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import co.edu.uniquindio.agenciaviajes.ui.Vista;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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
		new Thread(this::inicializarDestinos).start();
	}

	private void inicializarDestinos() {
		Destino[] destinos = new Destino[6];
		MainPaneController.getInstance().ejecutarProcesoDoble(() -> {
			destinos[0] = Destino.builder().nombre("PokemonRojo").ciudad("Kanto").descripcion("Pokemon lagartija")
					.clima(Clima.SECO).build();

			destinos[1] = Destino.builder().nombre("PokemonAzul").ciudad("Kanto").descripcion("Pokemon tortuga")
					.clima(Clima.FRIO).build();

			destinos[2] = Destino.builder().nombre("PokemonVerde").ciudad("Kanto").descripcion("Pokemon plantica")
					.clima(Clima.TROPICAL).build();

			destinos[3] = Destino.builder().nombre("eevee").ciudad("Kanto").descripcion("Pokemon chimbita")
					.clima(Clima.POLAR).build();
			destinos[4] = Destino.builder().nombre("meltan").ciudad("Alola").descripcion("Pokemon tuerca")
					.clima(Clima.TROPICAL).build();

			destinos[5] = Destino.builder().nombre("impidimp").ciudad("galar").descripcion("Pokemon alejo")
					.clima(Clima.POLAR).build();

			try {
				destinos[0].addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/charmander.png");
				destinos[0].addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/charmeleon.png");
				destinos[0].addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/charizard.png");
				destinos[0].addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/charizardGigamax.png");

				destinos[1].addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/squirtle.png");

				destinos[2].addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/hola3.png");

				destinos[3].addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/eevee.png");

				destinos[4].addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/meltan.png");

				destinos[5].addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/impidimp.png");
			} catch (ImagenNoObtenidaException e) {
				e.printStackTrace();
			}

			for (Destino destino : destinos) {
				for (int i = 0; i < 11; i++) {
					destino.addComentario(Comentario.builder()
							.cliente(Cliente.builder().identificacion("c" + i).email("email").telefono("321234345")
									.direccion("calle 21").contrasena("1234").nombreCompleto("Alejo").build())
							.comentario("HOla").puntuacion((int) (Math.random() * 5)).build());
				}
			}
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
		FadeTransition anim = new FadeTransition(Duration.millis(750), parent);
		anim.setFromValue(0);
		anim.setToValue(1);
		contentPane.add(parent, colIndex, rowIndex);
		anim.play();
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
