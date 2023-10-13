/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import co.edu.uniquindio.agenciaViajes.exceptions.ImagenNoExistenteException;
import co.edu.uniquindio.agenciaViajes.exceptions.ImagenYaExistenteException;
import co.edu.uniquindio.agenciaViajes.exceptions.PaqueteNoExistenteException;
import co.edu.uniquindio.agenciaViajes.exceptions.PaqueteYaExistenteException;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "destinos")
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Destino {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@ToString.Include
	private Long id;

	@NonNull
	@ToString.Include
	private String nombre;

	@NonNull
	@ToString.Include
	private String ciudad;

	@NonNull
	@ToString.Include
	private String descripcion;

	@ElementCollection
	@Lob
	private List<String> imagenes;

	@Enumerated(EnumType.STRING)
	@ToString.Include
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
	private Destino(String nombre, String ciudad, String descripcion, Clima clima) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.descripcion = descripcion;
		this.clima = clima;
		this.imagenes = new ArrayList<String>();
		this.paquetes = new ArrayList<Paquete>();
	}
	
	

	/**
	 * Verifica de manera recursiva para ver si el paquete con el id introducido por
	 * parametro existe en la lista. Retorna un valor booleano segun la busqueda.
	 * 
	 * @param id
	 * @param i
	 * @return
	 */
	private boolean verificarPaqueteAux(Long id, int i) {
		if (paquetes.get(i).getId().equals(id))
			return true;
		if (paquetes.size() == i)
			return false;
		return verificarPaqueteAux(id, ++i);
	}

	/**
	 * Verifica si un {@link Paquete} esta en la lista. Retorna un valor booleano
	 * como resultado.
	 * 
	 * @param id
	 * @return
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
	 */
	private void throwPaqueteYaExistente(Long id) throws PaqueteYaExistenteException {
		if (verificarPaquete(id))
			throw new PaqueteYaExistenteException(
					"El paquete de id:" + id.toString() + ", ya existe en la lista del objeto.");
	}

	/**
	 * Busca de forma recursiva en la lista y retorna el {@link Paquete}
	 * identificado con el id del parametro.
	 * 
	 * @param id
	 * @param i
	 * @return
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
	 * Lanza una {@link PaqueteNoExistenteException} si el paquete no existe en la
	 * lista.
	 * 
	 * @param id
	 * @return
	 * @throws PaqueteNoExistenteException
	 */
	public Paquete buscarPaquete(Long id) throws PaqueteNoExistenteException {
		throwPaqueteNoExistente(id);
		return buscarPaqueteAux(id, 0);
	}

	/**
	 * Agrega un nuevo paquete a la lista. Lanza una
	 * {@link PaqueteYaExistenteException} si el paquete ya existe en la lista.
	 * 
	 * @param paquete
	 * @throws PaqueteYaExistenteException
	 */
	public void agregarPaquete(Paquete paquete) throws PaqueteYaExistenteException {
		throwPaqueteYaExistente(paquete.getId());
		paquetes.add(paquete);
	}

	/**
	 * Busca y actualiza de forma recursiva un {@link Paquete} en la lista.
	 * 
	 * @param paquete
	 * @param i
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
	 * {@link PaqueteNoExistenteException} si el paquete no existe en la lista.
	 * 
	 * @param paquete
	 * @throws PaqueteNoExistenteException
	 */
	public void actualizarPaquete(Paquete paquete) throws PaqueteNoExistenteException {
		throwPaqueteNoExistente(paquete.getId());
		actualizarPaqueteAux(paquete, 0);
	}

	/**
	 * Busca y elimina de forma recursiva el {@link Paquete} identificado con el id
	 * recibido por parametro.
	 * 
	 * @param id
	 * @param i
	 */
	private void eliminarPaqueteAux(Long id, int i) {
		if (paquetes.size() == i)
			return;
		if (!paquetes.get(i).getId().equals(id))
			eliminarPaqueteAux(id, i + 1);
		paquetes.remove(i);
		return;
	}

	/**
	 * Elimina un {@link Paquete} identificado con el id recibido por parametro.
	 * Lanza una {@link PaqueteNoExistenteException} si el paquete no existe en la
	 * lista.
	 * 
	 * @param id
	 * @throws PaqueteNoExistenteException
	 */
	public void eliminarPaquete(Long id) throws PaqueteNoExistenteException {
		throwPaqueteNoExistente(id);
		eliminarPaqueteAux(id, 0);
	}
	
	private boolean verificarImagen(String image) {
		return imagenes.contains(image);
	}
	
	private int buscarIndexImagenAux(String imagen, int i) {
		if(imagenes.get(i).equals(imagen)) return i;
		if(imagenes.size() == i) return -1;
		return buscarIndexImagenAux(imagen, ++i);
	}
	
	public int buscarIndexImagen(String imagen) {
		return buscarIndexImagenAux(imagen, 0);
	}
	
	private void throwImagenYaExistente(String image) throws ImagenYaExistenteException {
		if(verificarImagen(image)) throw new ImagenYaExistenteException("La imagen ya existe en la lista");
	}
	
	private void throwImagenNoExistente(String image) throws ImagenNoExistenteException {
		if(!verificarImagen(image)) throw new ImagenNoExistenteException("La imagen no existe en la lista");
	}
	
	public void addImagen(String msg) throws ImagenYaExistenteException {
		throwImagenYaExistente(msg);
		imagenes.add(msg);
	}
	
	public void removeImagen(String imagen) throws ImagenNoExistenteException {
		throwImagenNoExistente(imagen);
		imagenes.remove(buscarIndexImagen(imagen));
	}
}
