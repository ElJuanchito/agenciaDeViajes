package co.edu.uniquindio.agenciaviajes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import co.edu.uniquindio.agenciaviajes.exceptions.IdiomaNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.IdiomaYaExistenteException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "guias")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class GuiaTuristico extends Usuario implements Comentable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NonNull
	@ElementCollection(targetClass = Idioma.class)
	private List<Idioma> idiomas;
	@NonNull
	private Integer expHoras;

	@OneToOne
	private Imagen imagen;

	private List<Reserva> reservas;
	private Map<Cliente, Comentario> mapComentarios;

	@Builder
	private GuiaTuristico(String identificacion, String nombreCompleto, Integer expHoras, Imagen imagen,
			Idioma... idiomas) {
		super(identificacion, nombreCompleto);
		this.expHoras = expHoras;
		this.idiomas = new ArrayList<Idioma>(List.of(idiomas));
		this.imagen = imagen;
		this.reservas = new ArrayList<Reserva>();
		this.mapComentarios = new HashMap<Cliente, Comentario>();
	}

	/**
	 * Busca y verifica de forma recursiva si un {@link Idioma} esta dentro del
	 * 
	 * @param idioma
	 * @param i
	 * @return
	 */
	private boolean verificarIdiomaAux(Idioma idioma, int i) {
		if (idiomas.get(0) == idioma)
			return true;
		if (idiomas.size() == i)
			return false;
		return verificarIdiomaAux(idioma, ++i);
	}

	/**
	 * Verifica si existe un {@link Idioma} dentro de la lista. Retorna un valor
	 * booleano dependiendo de la busqueda.
	 * 
	 * @param idioma
	 * @return
	 */
	public boolean verificarIdioma(Idioma idioma) {
		return verificarIdiomaAux(idioma, 0);
	}

	private void throwIdiomaYaExistente(Idioma idioma) throws IdiomaYaExistenteException {
		if (verificarIdioma(idioma))
			throw new IdiomaYaExistenteException("El idioma: " + idioma.toString() + "ya existe dentro de la lista.");
	}

	private void throwIdiomaNoExistente(Idioma idioma) throws IdiomaNoExistenteException {
		if (!verificarIdioma(idioma))
			throw new IdiomaNoExistenteException("El idioma: " + idioma.toString() + "no existe dentro de la lista.");
	}

	/**
	 * Agrega un {@link Idioma} a la lista. Lanza una
	 * {@link IdiomaYaExistenteException} si el {@link Idioma} ya existe en la
	 * lista.
	 * 
	 * @param idioma
	 * @throws IdiomaYaExistenteException
	 */
	public void agregarIdioma(Idioma idioma) throws IdiomaYaExistenteException {
		throwIdiomaYaExistente(idioma);
		idiomas.add(idioma);
	}

	/**
	 * Elimina un {@link Idioma} de la lista. Lanza una
	 * {@link IdiomaNoExistenteException} si el {@link Idioma} no existe en la
	 * lista.
	 * 
	 * @param idioma
	 * @throws IdiomaNoExistenteException
	 */
	public void eliminarIdioma(Idioma idioma) throws IdiomaNoExistenteException {
		throwIdiomaNoExistente(idioma);
		idiomas.remove(idioma);
	}

	@Override
	public boolean clientePuedeComentar(Cliente cliente) {
		return clienteFueGuia(cliente, this, 0);
	}

	private boolean clienteFueGuia(Cliente cliente, GuiaTuristico guiaTuristico, int i) {
		if (i >= reservas.size())
			return false;
		if (reservas.get(i).clienteEstuvoGuia(cliente, guiaTuristico))
			return true;
		return clienteFueGuia(cliente, guiaTuristico, i + 1);
	}

	private String getCadenaIdiomas() {
		return getCadenaIdiomasRecu("", 0);
	}

	private String getCadenaIdiomasRecu(String cad, int i) {
		if (i >= idiomas.size())
			return cad;
		cad += idiomas.get(i).getIdioma() + " ";
		return getCadenaIdiomasRecu(cad, i + 1);
	}

	public String getDescripcion() {
		String cadIdi = getCadenaIdiomas();
		String cadDescription = "Idiomas Hablados: ";
		cadDescription += cadIdi + "\n";
		cadDescription += "Experiencia: ";
		cadDescription += expHoras + " Horas";
		return cadDescription;
	}
}
