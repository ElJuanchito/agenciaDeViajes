/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaviajes.tests;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

/**
 * 
 * @author ElJuancho
 */
public class Jsonizador {
	
	@Test
	public void jsonizar() {
		Pokemon pokemon = new Pokemon("Charizard", Tipo.AGUA);

		ObjectMapper mapeador = new ObjectMapper();
		
		try {
			mapeador.writeValue(new File("src/test/resources/co/edu/uniquindio/agenciaviajes/jsons/boo.json"), pokemon);
		} catch (StreamWriteException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void desjsonizar() {
		ObjectMapper mapeador = JsonMapper.builder()
                .configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true)
                .build();
		try {
			Pokemon pokemon = mapeador.readValue(new File("src/test/resources/co/edu/uniquindio/agenciaviajes/jsons/boo.json"), Pokemon.class);
			System.out.println(pokemon.toString());
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
