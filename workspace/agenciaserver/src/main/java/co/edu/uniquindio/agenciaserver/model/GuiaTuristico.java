package co.edu.uniquindio.agenciaserver.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import co.edu.uniquindio.agenciaserver.exceptions.IdiomaNoExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.IdiomaYaExistenteException;
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
public class GuiaTuristico extends Usuario {
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

	/**
	 * @param identificacion
	 * @param nombreCompleto
	 * @param expHoras
	 */
	@Builder
	private GuiaTuristico(String identificacion, String nombreCompleto, Integer expHoras, Imagen imagen,
			Idioma... idiomas) {
		super(identificacion, nombreCompleto);
		this.expHoras = expHoras;
		this.idiomas = new ArrayList<Idioma>(List.of(idiomas));
		this.imagen = imagen;
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

}
