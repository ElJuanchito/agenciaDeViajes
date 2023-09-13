/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author ElJuancho
 */
@Entity
@Table(name = "destinos")
@NoArgsConstructor
@Setter @Getter
public class Destino {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String nombre;
	private String ciudad;
	private String descripcion;
	
	//Sujeto a revision
	private byte[][] imagenes;
	private Clima clima;
	
	@ManyToMany(mappedBy = "destinos")
    private Set<Paquete> paquetes = new HashSet<Paquete>();
	
	/**
	 * @param nombre
	 * @param ciudad
	 * @param descripcion
	 * @param imagenes
	 * @param clima
	 * @author ElJuancho
	 */
	@Builder
	private Destino(String nombre, String ciudad, String descripcion, Clima clima) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.descripcion = descripcion;
		this.clima = clima;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Destino other = (Destino) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Destino [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", descripcion=" + descripcion
				+ ", imagenes=" + Arrays.toString(imagenes) + ", clima=" + clima + "]";
	}
	
	
}
