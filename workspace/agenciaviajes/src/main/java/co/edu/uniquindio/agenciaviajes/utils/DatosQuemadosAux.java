package co.edu.uniquindio.agenciaviajes.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.agenciaviajes.exceptions.DestinoYaExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.ImagenNoObtenidaException;
import co.edu.uniquindio.agenciaviajes.model.Administrador;
import co.edu.uniquindio.agenciaviajes.model.Cliente;
import co.edu.uniquindio.agenciaviajes.model.Clima;
import co.edu.uniquindio.agenciaviajes.model.Comentario;
import co.edu.uniquindio.agenciaviajes.model.Destino;
import co.edu.uniquindio.agenciaviajes.model.GuiaTuristico;
import co.edu.uniquindio.agenciaviajes.model.Idioma;
import co.edu.uniquindio.agenciaviajes.model.Imagen;
import co.edu.uniquindio.agenciaviajes.model.Paquete;
import javafx.scene.image.Image;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DatosQuemadosAux {
	private static DatosQuemadosAux instance;
	private Paquete paquete;
	private List<GuiaTuristico> listaguias;

	private Administrador administrador;
	private List<GuiaTuristico> listaGuias;
	private Cliente cliente;

	public static DatosQuemadosAux getInstance() {
		if (instance == null)
			instance = new DatosQuemadosAux();
		return instance;
	}

	public Paquete getPaquete() {
		if (paquete != null)
			return paquete;
		paquete = Paquete.builder().fechaFin(LocalDateTime.now().plusMonths(10)).fechaIncio(LocalDateTime.now())
				.cupoMaximo(100).duracionDias(300).nombre("Paquete chileno").precio(BigDecimal.valueOf(10000000))
				.serviciosAdicionales("Ninguno")
				.descripcion(
						"Sumérgete en la magia de Cartagena, donde la historia cobra vida a través de sus calles empedradas y edificios coloniales meticulosamente conservados. Esta ciudad costera combina perfectamente el encanto del viejo mundo con la energía contemporánea, creando un destino turístico que enamora a todos sus visitantes.")
				.descripcionCorta("Encanto caribeño, historia viva, playas paradisíacas y gastronomía exquisita.")
				.build();

		Destino[] destinos = new Destino[6];
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

		try {

			for (int j = 0; j < destinos.length; j++) {
				Destino destino = destinos[j];
				destino.setId((long) j);
				for (int i = 0; i < 11; i++) {
					destino.addComentario(Comentario.builder()
							.cliente(Cliente.builder().identificacion("c" + i).email("email").telefono("321234345")
									.direccion("calle 21").contrasena("1234").nombreCompleto("Alejo").build())
							.comentario("HOla").puntuacion((int) (Math.random() * 5)).build());
				}
				paquete.agregarDestino(destino);
			}
		} catch (DestinoYaExistenteException e) {
			throw new RuntimeException(e);
		}
		return paquete;
	}

	public List<Paquete> obtenerListaPaquetes() {
		List<Paquete> listaPaquetes = new ArrayList<>();

		Paquete paquete1 = getPaquete();
		Paquete paquete2 = Paquete.builder().fechaFin(LocalDateTime.now().plusMonths(12))
				.fechaIncio(LocalDateTime.now()).cupoMaximo(150).duracionDias(320).nombre("Paquete Santa Marta")
				.precio(BigDecimal.valueOf(8500000)).serviciosAdicionales("Tour de buceo")
				.descripcion(
						"Disfruta del encanto histórico y natural de Santa Marta, rodeado de hermosas playas, sierras majestuosas y una rica cultura local. Sumérgete en las aguas cristalinas del Caribe y descubre la belleza submarina en un inolvidable tour de buceo.")
				.descripcionCorta("Playas impresionantes, sierras majestuosas y cultura local vibrante.").build();

		Paquete paquete3 = Paquete.builder().fechaFin(LocalDateTime.now().plusMonths(9)).fechaIncio(LocalDateTime.now())
				.cupoMaximo(120).duracionDias(280).nombre("Paquete San Andrés").precio(BigDecimal.valueOf(12000000))
				.serviciosAdicionales("Clases de surf")
				.descripcion(
						"Descubre el paraíso tropical de San Andrés, con sus impresionantes playas de arena blanca y aguas cristalinas de color turquesa. Disfruta de emocionantes clases de surf y sumérgete en la rica cultura local mientras te relajas en este destino de ensueño.")
				.descripcionCorta("Playas de ensueño, aguas turquesas y emocionantes clases de surf.").build();

		paquete2.setDestinos(paquete1.getDestinos());
		paquete3.setDestinos(paquete1.getDestinos());

		listaPaquetes.add(paquete1);
		listaPaquetes.add(paquete2);
		listaPaquetes.add(paquete3);

		return listaPaquetes;
	}

	public void agregarGuia(GuiaTuristico guia) {
		if (listaguias == null)
			listaguias = new ArrayList<GuiaTuristico>();
		listaguias.add(guia);
		System.out.println(guia);
	}

	public List<GuiaTuristico> obtenerListaGuias() {
		if (listaGuias != null)
			return listaGuias;
		listaGuias = new ArrayList<>();

		List<Idioma> listaIdiomas = new ArrayList<Idioma>();
		listaIdiomas.add(Idioma.ESPANOL);
		listaIdiomas.add(Idioma.INGLES);
		listaIdiomas.add(Idioma.ITALIANO);

		// No se porque dice que idiomas debe ser un arreglo y no una lista cuando esta
		// puesto como una lista creo que es por
		// Idioma...
		GuiaTuristico guia1 = null;
		try {
			guia1 = GuiaTuristico.builder().identificacion("1098675678").nombreCompleto("Duque Inminente").expHoras(89)
					.idiomas(listaIdiomas.stream().toArray(Idioma[]::new))
					.imagen(Imagen.createImage(new Image(getClass()
							.getResourceAsStream("/co/edu/uniquindio/agenciaviajes/imagenes/pikachuPiloto.png"))))
					.build();
		} catch (ImagenNoObtenidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		listaGuias.add(guia1);
		return listaGuias;
	}

	public Cliente getCliente() {
		try {
			if (cliente != null)
				return cliente;
			cliente = Cliente.builder().contrasena("hola").direccion("direccion").email("email").identificacion("id")
					.nombreCompleto("Alejo Perdomo").telefono("312345")
					.imagen(Imagen.createImage(new Image(instance.getClass()
							.getResource("/co/edu/uniquindio/agenciaviajes/imagenes/hola.jpg").toExternalForm())))
					.build();
			return cliente;
		} catch (ImagenNoObtenidaException e) {
			throw new RuntimeException(e);
		}
	}

	public Administrador getAdministrador() {
		try {
			if (administrador != null)
				return administrador;
			administrador = Administrador.builder().contrasena("hola").identificacion("id")
					.nombreCompleto("Alejo Perdomo Admin")
					.imagen(Imagen.createImage(new Image(instance.getClass()
							.getResource("/co/edu/uniquindio/agenciaviajes/imagenes/hola.jpg").toExternalForm())))
					.build();
			return administrador;
		} catch (ImagenNoObtenidaException e) {
			throw new RuntimeException(e);
		}
	}

}
