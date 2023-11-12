/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaviajes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.uniquindio.agenciaviajes.exceptions.ImagenNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.ImagenNoObtenidaException;
import co.edu.uniquindio.agenciaviajes.exceptions.PaqueteNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.PaqueteYaExistenteException;
import co.edu.uniquindio.agenciaviajes.utils.MathUtils;
import javafx.scene.image.Image;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Destino implements Comentable , Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	private List<Imagen> imagenes;

	@ToString.Include
	private Clima clima;

	@ToString.Include
	private TipoDestino tipoDestino;

	private Map<Cliente, Comentario> mapComentarios;

	private List<Paquete> paquetes;

	private List<Reserva> reservas;

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
		this.imagenes = new ArrayList<Imagen>();
		this.paquetes = new ArrayList<Paquete>();
		this.reservas = new ArrayList<Reserva>();
		this.mapComentarios = new HashMap<Cliente, Comentario>();
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

	private int buscarIndexImagenAux(Imagen imagen, int i) {
		if (imagenes.get(i).equals(imagen))
			return i;
		if (imagenes.size() == i)
			return -1;
		return buscarIndexImagenAux(imagen, ++i);
	}

	public int buscarIndexImagen(Imagen imagen) {
		return buscarIndexImagenAux(imagen, 0);
	}

	public void addImagen(Imagen imagen) {
		imagenes.add(imagen);
	}

	public void addImagen(String resource) throws ImagenNoObtenidaException {
		addImagen(Imagen.createImage(new Image(getClass().getResourceAsStream(resource))));
	}

	public void removeImagen(Imagen imagen) throws ImagenNoExistenteException {
		if (!imagenes.remove(imagen))
			throw new ImagenNoExistenteException("La imagen no existe en la lista");
	}

	public double getPromedio() {
		int cant = mapComentarios.size();
		if (cant == 0)
			return 5;
		return MathUtils.round(
				(mapComentarios.entrySet().stream().mapToDouble(t -> t.getValue().getPuntuacion()).sum() + 0d) / cant,
				1);
	}

	public void addComentario(Comentario comentario) {
		mapComentarios.put(comentario.getCliente(), comentario);
	}

	public int equivaleciaPref(Preferencia preferencia) {
		int cont = 0;
		if (clima == preferencia.getClima())
			cont++;
		if (tipoDestino == preferencia.getTipoDestino())
			cont++;
		return cont;
	}

	public void addReserva(Reserva reserva) {
		reservas.add(reserva);
	}

	public boolean clienteFueDestino(Cliente cliente, int i) {
		if (i >= reservas.size())
			return false;
		if (reservas.get(i).clienteEstuvoAlli(cliente))
			return true;
		return clienteFueDestino(cliente, i + 1);
	}

	@Override
	public boolean clientePuedeComentar(Cliente cliente) {
		return clienteFueDestino(cliente, 0);
	}
}
