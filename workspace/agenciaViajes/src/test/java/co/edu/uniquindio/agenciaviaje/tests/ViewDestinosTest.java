/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaviaje.tests;

import org.junit.Test;

import co.edu.uniquindio.agenciaviaje.exceptions.ImagenNoObtenidaException;
import co.edu.uniquindio.agenciaviaje.model.Clima;
import co.edu.uniquindio.agenciaviaje.model.Destino;

/**
 * 
 * @author ElJuancho
 */
public class ViewDestinosTest {

	@Test
	public void pruebaDestinos() throws ImagenNoObtenidaException {
		Destino destino1 = Destino.builder().nombre("PokemonRojo").ciudad("Kanto").descripcion("Pokemon lagartija")
				.clima(Clima.SECO).build();

		Destino destino2 = Destino.builder().nombre("PokemonAzul").ciudad("Kanto").descripcion("Pokemon tortuga")
				.clima(Clima.FRIO).build();

		Destino destino3 = Destino.builder().nombre("PokemonVerde").ciudad("Kanto").descripcion("Pokemon plantica")
				.clima(Clima.TROPICAL).build();

		destino1.addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/charmander.png");
		destino2.addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/squirtle.png");
		destino3.addImagen("/co/edu/uniquindio/agenciaviajes/imagenes/bulbasaur.png");

		System.out.println(destino1);
		System.out.println(destino2);
		System.out.println(destino3);
	}

	@Test
	public void pathTest() {
		String path = ViewDestinosTest.class.getResource("/co/edu/uniquindio/agenciaviajes/imagenes/charmander.png")
				.toExternalForm();
		System.out.println(path);
	}
}
