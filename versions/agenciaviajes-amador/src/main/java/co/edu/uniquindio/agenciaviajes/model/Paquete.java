package co.edu.uniquindio.agenciaviajes.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import co.edu.uniquindio.agenciaviajes.exceptions.DestinoNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.DestinoYaExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.PaqueteNoExistenteException;
import co.edu.uniquindio.agenciaviajes.utils.MathUtils;
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
@ToString
public class Paquete implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
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

	@NonNull
	private String descripcionCorta, descripcion;

	private List<Destino> destinos;

	private List<Reserva> reservas;

	/**
	 * @param nombre
	 * @param duracionDias
	 * @param serviciosAdicionales
	 * @param precio
	 * @param cupoMaximo
	 * @param fechaIncio
	 * @param fechaFin
	 */
	@Builder
	private Paquete(String nombre, Integer duracionDias, String serviciosAdicionales, BigDecimal precio,
			Integer cupoMaximo, LocalDateTime fechaIncio, LocalDateTime fechaFin, String descripcionCorta,
			String descripcion) {
		super();
		this.nombre = nombre;
		this.duracionDias = duracionDias;
		this.serviciosAdicionales = serviciosAdicionales;
		this.precio = precio;
		this.cupoMaximo = cupoMaximo;
		this.fechaIncio = fechaIncio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
		this.descripcionCorta = descripcionCorta;
		this.destinos = new ArrayList<Destino>();
		this.reservas = new ArrayList<Reserva>();

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
		if (destinos.size() == i)
			return false;
		if (destinos.get(i).getId().equals(id))
			return true;
		return verificarDestinoAux(id, ++i);
	}

	/**
	 * Verifica si un {@link Destino} esta en la lista. Retorna un valor booleano
	 * como resultado.
	 * 
	 * @param id
	 * @return
	 */
	public boolean verificarDestino(Long id) {
		if (destinos.isEmpty())
			return false;
		return verificarDestinoAux(id, 0);
	}

	/**
	 * Lanza una exception si el {@link Destino} con el id introducido por parametro
	 * no existe en la lista.
	 * 
	 * @param id
	 * @throws DestinoNoExistenteException
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
	 * Busca de forma recursiva en la lista y retorna el {@link Destino}
	 * identificado con el id del parametro.
	 * 
	 * @param id
	 * @param i
	 * @return
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
	 * Lanza una {@link PaqueteNoExistenteException} si el destino no existe en la
	 * lista.
	 * 
	 * @param id
	 * @throws DestinoNoExistenteException
	 * @return
	 */
	public Destino buscarDestino(Long id) throws DestinoNoExistenteException {
		throwDestinoNoExistente(id);
		return buscarDestinoAux(id, 0);
	}

	/**
	 * Agrega un nuevo destino a la lista. Lanza una
	 * {@link DestinoYaExistenteException} si el destino ya existe en la lista.
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
	 * Busca y actualiza de forma recursiva un {@link Destino} en la lista.
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
	 * {@link DestinoNoExistenteException} si el Destino no existe en la lista.
	 * 
	 * @param destino
	 * @throws DestinoNoExistenteException
	 */
	public void actualizarDestino(Destino destino) throws DestinoNoExistenteException {
		throwDestinoNoExistente(destino.getId());
		actualizarDestinoAux(destino, 0);
	}

	/**
	 * Busca y elimina de forma recursiva el {@link Destino} identificado con el id
	 * recibido por parametro.
	 * 
	 * @param id
	 * @param i
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
	 * Elimina un {@link Destino} identificado con el id recibido por parametro.
	 * Lanza una {@link DestinoNoExistenteException} si el destino no existe en la
	 * lista.
	 * 
	 * @param id
	 * @throws DestinoNoExistenteException
	 */
	public void eliminarDestino(Long id) throws DestinoNoExistenteException {
		throwDestinoNoExistente(id);
		elminarDestinoAux(id, 0);
	}

	private List<Imagen> getDestinosImages(List<Imagen> imagenes, int i) {
		if (i == destinos.size())
			return imagenes;
		imagenes.add(destinos.get(i).getImagenes().get(0));
		return getDestinosImages(imagenes, i + 1);

	}

	public List<Imagen> listarImagenesDestino() {
		List<Imagen> imagenes = new ArrayList<Imagen>();
		return getDestinosImages(imagenes, 0);

	}

	private double getAcumPromedios(double promedio, int i) {
		if (i == destinos.size())
			return promedio;
		return getAcumPromedios(promedio + destinos.get(i).getPromedio(), i + 1);
	}

	public double getPromedioDestinos() {
		double prom = 5;
		if (destinos.size() != 0) {
			prom = (getAcumPromedios(0, 0)) / destinos.size();
		}
		return MathUtils.round(prom, 1);

	}

	public boolean estaEntreFechas(LocalDate fechaBusquedaStart, LocalDate fechaBusquedaEnd) {
		return !fechaIncio.toLocalDate().isBefore(fechaBusquedaStart)
				&& !fechaIncio.toLocalDate().isAfter(fechaBusquedaEnd);
	}

	public boolean contieneClima(Clima clima) {
		return contieneClima(clima, 0);
	}

	private boolean contieneClima(Clima clima, int i) {
		if (i == destinos.size())
			return false;
		if (destinos.get(i).getClima() == clima)
			return true;
		return contieneClima(clima, i + 1);
	}

	public boolean contieneCiudad(String nombreCiudad) {
		return contieneCiudad(nombreCiudad.toLowerCase(), 0);
	}

	private boolean contieneCiudad(String nombreCiudad, int i) {
		if (i == destinos.size())
			return false;
		if (destinos.get(i).getCiudad().toLowerCase().contains(nombreCiudad))
			return true;
		return contieneCiudad(nombreCiudad, i + 1);
	}

	public boolean contieneNombreDestino(String nombre) {
		return contieneNombreDestino(nombre, 0);
	}

	private boolean contieneNombreDestino(String nombreCiudad, int i) {
		if (i == destinos.size())
			return false;
		if (destinos.get(i).getNombre().toLowerCase().contains(nombreCiudad))
			return true;
		return contieneCiudad(nombreCiudad, i + 1);
	}

	public boolean tieneNombre(String nombre) {
		return this.nombre.equals(nombre);
	}

	public boolean tienePrecioEntre(Double desde, Double hasta) {
		return precio.doubleValue() >= desde && precio.doubleValue() <= hasta;
	}

}
