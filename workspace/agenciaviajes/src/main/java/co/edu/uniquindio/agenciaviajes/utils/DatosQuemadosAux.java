package co.edu.uniquindio.agenciaviajes.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import co.edu.uniquindio.agenciaviajes.exceptions.DestinoYaExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.ImagenNoObtenidaException;
import co.edu.uniquindio.agenciaviajes.model.Cliente;
import co.edu.uniquindio.agenciaviajes.model.Clima;
import co.edu.uniquindio.agenciaviajes.model.Comentario;
import co.edu.uniquindio.agenciaviajes.model.Destino;
import co.edu.uniquindio.agenciaviajes.model.Paquete;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DatosQuemadosAux {
	private static DatosQuemadosAux instance;
	private Paquete paquete;

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

}
