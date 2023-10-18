/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import org.junit.Test;

import co.edu.uniquindio.agenciaViajes.tests.TextTest.Jugador.Posicion;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author ElJuancho
 */
public class TextTest {

	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	@Data
	public class Jugador {

		@AllArgsConstructor
		public enum Posicion {
			PORTERO("portero"), DEFENSA("defensa"), VOLANTE("volante"), CENTROCAMPISTA("centrocampista"),
			DELANTERO("delantero");

			@Getter
			private String text;

			public static Posicion getPosicionByString(String valor) {
				for (Posicion pos : values()) {
					if (pos.getText().equals(valor))
						return pos;
				}
				return null;
			}
		}

		private String nombre;
		private String numero;
		private String nacionalidad;
		private Posicion posicion;

		public void setPosicion(String posicion) {
			this.posicion = Posicion.getPosicionByString(posicion);
		}

	}

	/**
	 * Test para escribir datos en un archivo plano.<br>
	 * Principalmente se utilizaron los objetos {@link FileWriter} y
	 * {@link BufferedWriter} para realizar la escritura. Tambien se utilizo el
	 * metodo {@link String#format(String, Object...)} para darle el formato deseado
	 * a los datos que se quieren guardar.
	 * 
	 * @author ElJuancho
	 */
	@Test
	public void escribir() {
		String ruta = "src/test/resources/salida.txt";

		Jugador[] jugadores = new Jugador[3];
		jugadores[0] = new Jugador("James Rodriguez", "10", "Colombia", Posicion.CENTROCAMPISTA);
		jugadores[1] = new Jugador("Lionel Messi", "10", "Argentina", Posicion.DELANTERO);
		jugadores[2] = new Jugador("Marc-André ter Stegen", "1", "Alemania", Posicion.PORTERO);

		try (FileWriter fileW = new FileWriter(ruta, true); BufferedWriter escritor = new BufferedWriter(fileW)) {
			for (Jugador jugador : jugadores) {
				escritor.write(String.format("El jugador %s (%s) de %s juega como %s", jugador.getNombre(),
						jugador.getNumero(), jugador.getNacionalidad(), jugador.getPosicion().getText()));
				escritor.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test para leer datos de un archivo plano. <br>
	 * <br>
	 * Principalmente se utilizaron los objetos {@link FileReader} y
	 * {@link BufferedReader} para la lectura. Tambien se utilizo el objeto
	 * {@link StringTokenizer} para separar cada linea del archivo en tokens. <br>
	 * <br>
	 * para recorrer todo el archivo se utilizo el metodo
	 * {@link BufferedReader#lines()} para obtener un <code>Stream<"String"></code>
	 * de todas las lineas. Luego se mapeo a un Stream<"Jugador"> utilizando el
	 * metodo {@link Stream#map(java.util.function.Function)} y el
	 * {@link StringTokenizer} para despues transforma el Stream en un arreglo de
	 * tipo Jugador.
	 * 
	 * @author ElJuancho
	 */
	@Test
	public void leer() {
		String ruta = "src/test/resources/entrada.txt";

		try (FileReader fileW = new FileReader(ruta); BufferedReader lector = new BufferedReader(fileW)) {

			Jugador[] jugadores = lector.lines().map(linea -> {
				var st = new StringTokenizer(linea, ";");
				return new Jugador(st.nextToken(), st.nextToken(), st.nextToken(),
						Posicion.getPosicionByString(st.nextToken()));
			}).toArray(Jugador[]::new);

			for (Jugador jugador : jugadores) {
				System.out.printf("El jugador es %s", jugador.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
