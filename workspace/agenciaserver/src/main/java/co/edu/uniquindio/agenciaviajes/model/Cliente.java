package co.edu.uniquindio.agenciaviajes.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import co.edu.uniquindio.agenciaviajes.exceptions.ReservaNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.ReservaYaExistenteException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cliente extends Usuario implements Loginable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NonNull
	private String email;
	@NonNull
	private String telefono;
	@NonNull
	private String direccion;

	@NonNull
	private String contrasena;

	@OneToMany
	private List<Reserva> reservas;

	private List<Preferencia> preferencias;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
	private Imagen imagen;

	@Builder
	private Cliente(@NonNull String identificacion, @NonNull String nombreCompleto, @NonNull String email,
			@NonNull String contrasena, @NonNull String telefono, @NonNull String direccion, Imagen imagen) {
		super(identificacion, nombreCompleto);
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
		this.contrasena = contrasena;
		this.imagen = imagen;
		reservas = new ArrayList<Reserva>();
	}

	/**
	 * Busca y verifica de forma recursiva si una reserva se encuentra en la lista.
	 * Retorna un valor boolean segun si lo encuentra o no.
	 * 
	 * @param id
	 * @param i
	 * @return
	 */
	private boolean verificarReservaAux(Long id, int i) {
		if (reservas.get(i).getId().equals(id))
			return true;
		if (i == reservas.size())
			return false;
		return verificarReservaAux(id, ++i);
	}

	/**
	 * Busca y verifica si una reserva se encuentra en la lista. Retorna un valor
	 * booleano.
	 * 
	 * @param id
	 * @return
	 */
	public boolean verificarReserva(Long id) {
		return verificarReservaAux(id, 0);
	}

	/**
	 * Lanza una {@link ReservaNoExistenteException} si la reserva no existe en la
	 * lista.
	 * 
	 * @param id
	 * @throws ReservaNoExistenteException
	 */
	private void throwReservaNoExistente(Long id) throws ReservaNoExistenteException {
		if (!verificarReserva(id))
			throw new ReservaNoExistenteException(
					"La reserva con el id:" + id.toString() + ", no existente en la lista");
	}

	/**
	 * Lanza una {@link ReservaYaExistenteException} si la reserva ya existe en la
	 * lista.
	 * 
	 * @param id
	 * @throws ReservaYaExistenteException
	 */
	private void throwReservaYaExistente(Long id) throws ReservaYaExistenteException {
		if (verificarReserva(id))
			throw new ReservaYaExistenteException(
					"La reserva con el id:" + id.toString() + ", ya existente en la lista");
	}

	/**
	 * Busca y retorna de manera recursiva la {@link Reserva} que posea el <b>id</b>
	 * que se recibio por parametro. Retorna un null en caso de no econtrarlo.
	 * 
	 * @param id
	 * @param i
	 * @return
	 */
	private Reserva buscarReservaAux(Long id, int i) {
		if (reservas.get(i).getId().equals(id))
			return reservas.get(i);
		if (i == reservas.size())
			return null;
		return buscarReservaAux(id, ++i);
	}

	/**
	 * Busca y retorna la <code>Reserva</code> que posea el <b>id</b> que se recibio
	 * por parametro. Lanza una <code>ReservaNoExistenteException</code> si la
	 * <code>Reserva</code> no existe en la lista.
	 * 
	 * @param id
	 * @return
	 * @throws ReservaNoExistenteException
	 * 
	 */
	public Reserva buscarReserva(Long id) throws ReservaNoExistenteException {
		throwReservaNoExistente(id);
		return buscarReservaAux(id, 0);
	}

	/**
	 * Agrega una nueva <code>Reserva</code> a la lista. Lanza una
	 * <code>{@link ReservaYaExistenteException} ReservaYaExistenteException</code>
	 * si la <code>Reserva</code> ya existe en la lista.
	 * 
	 * @param reserva
	 * @throws ReservaYaExistenteException
	 * 
	 */
	public void agregarReserva(Reserva reserva) throws ReservaYaExistenteException {
		throwReservaYaExistente(reserva.getId());
		reservas.add(reserva);
	}

	/**
	 * Busca y elimina de forma recursiva una <code>Reserva</code> de la lista.
	 * 
	 * @param id
	 * @param i
	 * 
	 */
	private void eliminarReservaAux(Long id, int i) {
		if (reservas.size() == i)
			return;
		if (!reservas.get(i).getId().equals(id))
			eliminarReservaAux(id, i + 1);
		reservas.remove(i);
	}

	/**
	 * Busca y elimina una <code>Reserva</code> de la lista. Lanza una
	 * <code>{@link ReservaNoExistenteException} ReservaNoExistenteException</code>
	 * si la <code>Reserva</code> no existe en la lista.
	 * 
	 * @param id
	 * @throws ReservaNoExistenteException
	 * 
	 */
	public void eliminarReserva(Long id) throws ReservaNoExistenteException {
		throwReservaNoExistente(id);
		eliminarReservaAux(id, 0);
	}

	public void agregarPreferencia(Destino destino) {
		Preferencia preferencia = Preferencia.builder().tipoDestino(destino.getTipoDestino()).clima(destino.getClima())
				.idDestino(destino.getId()).build();
		if (!preferencias.contains(preferencia))
			preferencias.add(preferencia);
	}

	public Preferencia getMayorPreferencia() {
		if (preferencias.isEmpty()) {
			return null;
		}

		Map<Clima, Integer> mapaClimas = new HashMap<>();
		Map<TipoDestino, Integer> mapaTipoDestinos = new HashMap<>();

		preferencias.forEach(preferencia -> {
			Clima clima = preferencia.getClima();
			TipoDestino tipoDestino = preferencia.getTipoDestino();

			mapaClimas.put(clima, mapaClimas.getOrDefault(clima, 0) + 1);
			mapaTipoDestinos.put(tipoDestino, mapaTipoDestinos.getOrDefault(tipoDestino, 0) + 1);
		});

		Map.Entry<Clima, Integer> entryClimaMayor = Collections.max(mapaClimas.entrySet(),
				Map.Entry.comparingByValue());
		Map.Entry<TipoDestino, Integer> entryTipoDestinoMayor = Collections.max(mapaTipoDestinos.entrySet(),
				Map.Entry.comparingByValue());

		return Preferencia.builder().clima(entryClimaMayor.getKey()).tipoDestino(entryTipoDestinoMayor.getKey())
				.build();
	}

	public List<Destino> listarDestinosOrdenPreferencia(List<Destino> destinos) {
		List<Destino> destinosSort = new ArrayList<Destino>();
		Preferencia preferencia = getMayorPreferencia();
		Collections.copy(destinosSort, destinos);
		destinosSort.sort((o1, o2) -> o1.equivaleciaPref(preferencia) - o2.equivaleciaPref(preferencia));
		return destinosSort;
	}

	@Override
	public String getUsuario() {
		return getIdentificacion();
	}

	public List<Reserva> getReservasPasadas(List<Reserva> seleccionadas, int i) {
		if (i >= reservas.size())
			return seleccionadas;
		Reserva reserva = reservas.get(i);
		if (esPasada(reserva))
			seleccionadas.add(reservas.get(i));
		return getReservasPasadas(seleccionadas, i + 1);
	}

	public List<Reserva> getReservasFuturas(List<Reserva> seleccionadas, int i) {
		if (i >= reservas.size())
			return seleccionadas;
		Reserva reserva = reservas.get(i);
		if (esFutura(reserva))
			seleccionadas.add(reservas.get(i));
		return getReservasFuturas(seleccionadas, i + 1);
	}

	private boolean esFutura(Reserva reserva) {
		return reserva.getPaquete().getFechaIncio().isAfter(LocalDateTime.now());
	}

	private boolean esPasada(Reserva reserva) {
		return reserva.getPaquete().getFechaFin().isBefore(LocalDateTime.now());
	}

}
