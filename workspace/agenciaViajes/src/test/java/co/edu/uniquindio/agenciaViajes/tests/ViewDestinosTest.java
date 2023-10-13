/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.tests;

import org.junit.Test;

import co.edu.uniquindio.agenciaViajes.application.App;
import co.edu.uniquindio.agenciaViajes.exceptions.ImagenYaExistenteException;
import co.edu.uniquindio.agenciaViajes.model.Clima;
import co.edu.uniquindio.agenciaViajes.model.Destino;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * 
 * @author ElJuancho
 */
public class ViewDestinosTest {
	
	@Test
	public void pruebaDestinos() {
		Destino destino1 = Destino.builder()
				.nombre("PokemonRojo")
				.ciudad("Kanto")
				.descripcion("Pokemon lagartija")
				.clima(Clima.SECO)
				.build();
		try {
			destino1.addImagen(ViewDestinosTest.class.getResource("/co/edu/uniquindio/agenciaviajes/imagenes/charmander.png").toExternalForm());
		} catch (ImagenYaExistenteException e) {
			e.printStackTrace();
		}
		
		Destino destino2 = Destino.builder()
				.nombre("PokemonAzul")
				.ciudad("Kanto")
				.descripcion("Pokemon tortuga")
				.clima(Clima.FRIO)
				.build();
		try {
			destino2.addImagen(ViewDestinosTest.class.getResource("/co/edu/uniquindio/agenciaviajes/imagenes/squirtle.png").toExternalForm());
		} catch (ImagenYaExistenteException e) {
			e.printStackTrace();
		}
		
		Destino destino3 = Destino.builder()
				.nombre("PokemonVerde")
				.ciudad("Kanto")
				.descripcion("Pokemon plantica")
				.clima(Clima.TROPICAL)
				.build();
		try {
			destino3.addImagen(ViewDestinosTest.class.getResource("/co/edu/uniquindio/agenciaviajes/imagenes/bulbasaur.png").toExternalForm());
		} catch (ImagenYaExistenteException e) {
			e.printStackTrace();
		}
		
		System.out.println(destino1);
		System.out.println(destino2);
		System.out.println(destino3);
	}
	
	public static Parent loadFXML(String fxml) throws Exception {
		return new FXMLLoader(App.class.getResource("/co/edu/uniquindio/agenciaviajes/fxml/" + fxml + ".fxml")).load();
	}
	
	@Test
	public void pathTest() {
		String path = ViewDestinosTest.class.getResource("/co/edu/uniquindio/agenciaviajes/imagenes/charmander.png").toExternalForm();
		System.out.println(path);
	}
}
