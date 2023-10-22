/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaviajes.tests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * @author ElJuancho
 */
public enum Tipo{
	FUEGO("Fuego"), AGUA("Agua"), PLANTA("Planta"), LUCHA("Lucha"), PSIQUICO("Psiquico"), SINIESTRO("Siniestro");
	
	private final String valor;
	
	Tipo(String valor) {
        this.valor = valor;
    }
	
	@JsonValue
    public String toValue() {
        return valor;
    }
	
	@JsonCreator
    public static Tipo fromValue(String valor) {
        for (Tipo tipo : values()) {
            if (tipo.valor.equals(valor)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo no válido: " + valor);
    }
}