/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import co.edu.uniquindio.agenciaViajes.exceptions.DestinoNoExistenteException;
import co.edu.uniquindio.agenciaViajes.exceptions.DestinoYaExistenteException;
import co.edu.uniquindio.agenciaViajes.services.RecurStrictList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * 
 * @author ElJuancho
 */
@Entity
@Table(name = "paquetes")
@NoArgsConstructor
@Setter
@Getter
public class Paquete {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long id;
	@NonNull
	private String nombre;
	@NonNull
	private Integer duracionDias;
	@NonNull
	private String serviciosAdicionales;
	@NonNull
	private BigDecimal precio;
	@NonNull
	private Integer cupoMaximo;
	@NonNull
	private LocalDateTime fechaIncio;
	@NonNull
	private LocalDateTime fechaFin;

	@ManyToMany
	@JoinTable(name = "paquete_destino", joinColumns = @JoinColumn(name = "paquete_id"), inverseJoinColumns = @JoinColumn(name = "destino_id"))
	private List<Destino> destinos;

	/**
	 * @param nombre
	 * @param duracionDias
	 * @param serviciosAdicionales
	 * @param precio
	 * @param cupoMaximo
	 * @param fechaIncio
	 * @param fechaFin
	 * @author ElJuancho
	 */
	@Builder
	public Paquete(String nombre, Integer duracionDias, String serviciosAdicionales, BigDecimal precio,
			Integer cupoMaximo, LocalDateTime fechaIncio, LocalDateTime fechaFin) {
		super();
		this.nombre = nombre;
		this.duracionDias = duracionDias;
		this.serviciosAdicionales = serviciosAdicionales;
		this.precio = precio;
		this.cupoMaximo = cupoMaximo;
		this.fechaIncio = fechaIncio;
		this.fechaFin = fechaFin;
		this.destinos = new RecurStrictList<Destino>();
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
		Paquete other = (Paquete) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Paquete [id=" + id + ", nombre=" + nombre + ", duracionDias=" + duracionDias + ", serviciosAdicionales="
				+ serviciosAdicionales + ", precio=" + precio + ", cupoMaximo=" + cupoMaximo + ", fechaIncio="
				+ fechaIncio + ", fechaFin=" + fechaFin + "]";
	}

// INICIO DEL CRUD:

	/**
	 * Verifica de manera recursiva para ver si el destino con el id introducido por
	 * parametro existe en la lista. Retorna un valor booleano segun la busqueda.
	 * 
	 * @param id
	 * @param i
	 * @return
	 * @author ElJuancho
	 */

	private boolean verificarDestinoAux(Long id, int i) {
		if (destinos.get(i).getId().equals(id))
			return true;
		if (destinos.size() == i)
			return false;
		return verificarDestinoAux(id, ++i);
	}

	/**
	 * Verifica si un Destino esta en la lista. Retorna un valor booleano como
	 * resultado.
	 * 
	 * @param id
	 * @return
	 * @author ElJuancho
	 */
	public boolean verificarDestino(Long id) {
		return verificarDestinoAux(id, 0);
	}

	/**
	 * Lanza una exception si el Destino con el id introducido por parametro no
	 * existe en la lista.
	 * 
	 * @param id
	 * @throws DestinoNoExistenteException
	 * @author ElJuancho
	 */
	private void throwDestinoNoExistente(Long id) throws DestinoNoExistenteException {
		if (!verificarDestino(id))
			throw new DestinoNoExistenteException(
					"El destino con id:" + id.toString() + ", no existe en la lista del objeto.");
	}

	/**
	 * Lanza una exception si el Destino con el id introducido por parametro ya
	 * existe en la lista.
	 * 
	 * @param id
	 * @throws DestinoYaExistenteException
	 * @author ElJuancho
	 */
	private void throwDestinoYaExistente(Long id) throws DestinoYaExistenteException {
		if (verificarDestino(id))
			throw new DestinoYaExistenteException(
					"El destino de id:" + id.toString() + ", ya existe en la lista del objeto.");
	}

	/**
	 * Busca de forma recursiva en la lista y retorna el destino identificado con el
	 * id del parametro.
	 * 
	 * @param id
	 * @param i
	 * @return
	 * @author ElJuancho
	 */
	private Destino buscarDestinoAux(Long id, int i) {
		if (destinos.size() == i)
			return null;
		if (destinos.get(i).getId().equals(id))
			return destinos.get(i);
		return buscarDestinoAux(id, ++i);
	}

	/**
	 * Busca y retorna el destino identificado con el id recibio por parametro.
	 * Lanza una <code>PaqueteNoExistenteException</code> si el destino no existe en
	 * la lista.
	 * 
	 * @param id
	 * @throws DestinoNoExistenteException
	 * @return
	 * @author ElJuancho
	 */
	public Destino buscarDestino(Long id) throws DestinoNoExistenteException {
		throwDestinoNoExistente(id);
		return buscarDestinoAux(id, 0);
	}

	/**
	 * Agrega un nuevo destino a la lista. Lanza una
	 * <code>DestinoYaExistenteException</code> si el destino ya existe en la lista.
	 * 
	 * @param destino
	 * @throws DestinoYaExistenteException
	 * @author ElJuancho
	 */
	public void agregarDestino(Destino destino) throws DestinoYaExistenteException {
		throwDestinoYaExistente(destino.getId());
		destinos.add(destino);
	}

	/**
	 * Busca y actualiza de forma recursiva un <code>Destino</code> en la lista.
	 * 
	 * @param destino
	 * @param i
	 * @author ElJuancho
	 */
	private void actualizarDestinoAux(Destino destino, int i) {
		if (destinos.size() == i)
			return;
		if (!destinos.get(i).equals(destino))
			actualizarDestinoAux(destino, i + 1);
		destinos.set(i, destino);
		return;
	}

	/**
	 * Actualiza un destino en la lista con sus nuevos cambios. Lanza una
	 * <code>DestinoNoExistenteException</code> si el Destino no existe en la lista.
	 * 
	 * @param destino
	 * @author ElJuancho
	 * @throws DestinoNoExistenteException
	 */
	public void actualizarDestino(Destino destino) throws DestinoNoExistenteException {
		throwDestinoNoExistente(destino.getId());
		actualizarDestinoAux(destino, 0);
	}

	/**
	 * Busca y elimina de forma recursiva el <code>Destino</code> identificado con
	 * el id recibido por parametro.
	 * 
	 * @param id
	 * @param i
	 * @author ElJuancho
	 */
	private void elminarDestinoAux(Long id, int i) {
		if (destinos.size() == i)
			return;
		if (!destinos.get(i).getId().equals(id))
			elminarDestinoAux(id, i + 1);
		destinos.remove(i);
		return;
	}

	/**
	 * Elimina un <code>Destino</code> identificado con el id recibido por
	 * parametro. Lanza una <code>DestinoNoExistenteException</code> si el destino
	 * no existe en la lista.
	 * 
	 * @param id
	 * @throws DestinoNoExistenteException
	 * @author ElJuancho
	 */
	public void eliminarDestino(Long id) throws DestinoNoExistenteException {
		throwDestinoNoExistente(id);
		elminarDestinoAux(id, 0);
	}

}
