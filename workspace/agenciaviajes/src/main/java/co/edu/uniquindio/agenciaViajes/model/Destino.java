/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import co.edu.uniquindio.agenciaViajes.exceptions.PaqueteNoExistenteException;
import co.edu.uniquindio.agenciaViajes.exceptions.PaqueteYaExistenteException;
import co.edu.uniquindio.agenciaViajes.services.RecurStrictList;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * 
 * @author ElJuancho
 */
@Entity
@Table(name = "destinos")
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Destino {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	@NonNull
	private String nombre;
	@NonNull
	private String ciudad;
	@NonNull
	private String descripcion;

	// Sujeto a revision
	transient private List<byte[]> imagenes;
	private Clima clima;

	@ManyToMany(mappedBy = "destinos")
	private List<Paquete> paquetes;

	/**
	 * @param nombre
	 * @param ciudad
	 * @param descripcion
	 * @param imagenes
	 * @param clima
	 * @author ElJuancho
	 */
	@Builder
	public Destino(String nombre, String ciudad, String descripcion, Clima clima) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.descripcion = descripcion;
		this.clima = clima;
		this.paquetes = new RecurStrictList<Paquete>();
	}

	@Override
	public String toString() {
		return "Destino [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", descripcion=" + descripcion
				+ ", imagenes=" + imagenes + ", clima=" + clima + "]";
	}

	/**
	 * Verifica de manera recursiva para ver si el paquete con el id introducido por
	 * parametro existe en la lista. Retorna un valor booleano segun la busqueda. 
	 * @param id
	 * @param i
	 * @return
	 * @author ElJuancho
	 */
	private boolean verificarPaqueteAux(Long id, int i) {
		if (paquetes.get(i).equals(id))
			return true;
		if (paquetes.size() == i)
			return false;
		return verificarPaqueteAux(id, ++i);
	}

	/**
	 * Verifica si un Paquete esta en la lista. Retorna un valor booleano como
	 * resultado.
	 * 
	 * @param id
	 * @return
	 * @author ElJuancho
	 */
	public boolean verificarPaquete(Long id) {
		return verificarPaqueteAux(id, 0);
	}

	/**
	 * Lanza una exception si el paquete con el id introducido por parametro no
	 * existe en la lista.
	 * 
	 * @param id
	 * @throws PaqueteNoExistenteException
	 * @author ElJuancho
	 */
	private void throwPaqueteNoExistente(Long id) throws PaqueteNoExistenteException {
		if (!verificarPaquete(id))
			throw new PaqueteNoExistenteException(
					"El paquete de id:" + id.toString() + ", no existe en la lista del objeto.");
	}

	/**
	 * Lanza una exception si el paquete con el id introducido por parametro ya
	 * existe en la lista.
	 * 
	 * @param id
	 * @throws PaqueteYaExistenteException
	 * @author ElJuancho
	 */
	private void throwPaqueteYaExistente(Long id) throws PaqueteYaExistenteException {
		if (verificarPaquete(id))
			throw new PaqueteYaExistenteException(
					"El paquete de id:" + id.toString() + ", ya existe en la lista del objeto.");
	}

	/**
	 * Busca de forma recursiva en la lista y retorna el paquete identificado con el
	 * id del parametro.
	 * 
	 * @param id
	 * @param i
	 * @return
	 * @author ElJuancho
	 */
	private Paquete buscarPaqueteAux(Long id, int i) {
		if (paquetes.size() == i)
			return null;
		if (paquetes.get(i).getId().equals(id))
			return paquetes.get(i);
		return buscarPaqueteAux(id, ++i);
	}

	/**
	 * Busca y retorna el paquete identificado con el id recibio por parametro.
	 * Lanza una <code>PaqueteNoExistenteException</code> si el paquete no existe en
	 * la lista.
	 * 
	 * @param id
	 * @return
	 * @throws PaqueteNoExistenteException
	 * @author ElJuancho
	 */
	public Paquete buscarPaquete(Long id) throws PaqueteNoExistenteException {
		throwPaqueteNoExistente(id);
		return buscarPaqueteAux(id, 0);
	}

	/**
	 * Agrega un nuevo paquete a la lista. Lanza una
	 * <code>PaqueteYaExistenteException</code> si el paquete ya existe en la lista.
	 * 
	 * @param paquete
	 * @throws PaqueteYaExistenteException
	 * @author ElJuancho
	 */
	public void agregarPaquete(Paquete paquete) throws PaqueteYaExistenteException {
		throwPaqueteYaExistente(paquete.getId());
		paquetes.add(paquete);
	}

	/**
	 * Busca y actualiza de forma recursiva un <code>Paquete</code> en la lista.
	 * 
	 * @param paquete
	 * @param i
	 * @author ElJuancho
	 */
	private void actualizarPaqueteAux(Paquete paquete, int i) {
		if (paquetes.size() == i)
			return;
		if (!paquetes.get(i).equals(paquete))
			actualizarPaqueteAux(paquete, i + 1);
		paquetes.set(i, paquete);
		return;
	}

	/**
	 * Actualiza un paquete en la lista con sus nuevos cambios. Lanza una
	 * <code>PaqueteNoExistenteException</code> si el paquete no existe en la lista.
	 * 
	 * @param paquete
	 * @author ElJuancho
	 * @throws PaqueteNoExistenteException
	 */
	public void actualizarPaquete(Paquete paquete) throws PaqueteNoExistenteException {
		throwPaqueteNoExistente(paquete.getId());
		actualizarPaqueteAux(paquete, 0);
	}

	/**
	 * Busca y elimina de forma recursiva el <code>Paquete</code> identificado con
	 * el id recibido por parametro.
	 * 
	 * @param id
	 * @param i
	 * @author ElJuancho
	 */
	private void elminarPaqueteAux(Long id, int i) {
		if (paquetes.size() == i)
			return;
		if (!paquetes.get(i).getId().equals(id))
			elminarPaqueteAux(id, i + 1);
		paquetes.remove(i);
		return;
	}

	/**
	 * Elimina un <code>Paquete</code> identificado con el id recibido por
	 * parametro. Lanza una <code>PaqueteNoExistenteException</code> si el paquete
	 * no existe en la lista.
	 * 
	 * @param id
	 * @throws PaqueteNoExistenteException
	 * @author ElJuancho
	 */
	public void eliminarPaquete(Long id) throws PaqueteNoExistenteException {
		throwPaqueteNoExistente(id);
		elminarPaqueteAux(id, 0);
	}

}
