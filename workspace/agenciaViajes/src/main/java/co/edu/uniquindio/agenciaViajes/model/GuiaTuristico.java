/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

import co.edu.uniquindio.agenciaViajes.exceptions.IdiomaNoExistenteException;
import co.edu.uniquindio.agenciaViajes.exceptions.IdiomaYaExistenteException;
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
@Table(name = "guias")
@Getter
@Setter
@NoArgsConstructor
public class GuiaTuristico extends Usuario {
	@NonNull
	@ElementCollection(targetClass = Idioma.class)
	private List<Idioma> idiomas = new RecurStrictList<Idioma>();
	@NonNull
	private Integer expHoras;

	/**
	 * @param identificacion
	 * @param nombreCompleto
	 * @param expHoras
	 * @author ElJuancho
	 */
	@Builder
	public GuiaTuristico(Long identificacion, String nombreCompleto, Integer expHoras, List<Idioma> idiomas) {
		super(identificacion, nombreCompleto);
		this.expHoras = expHoras;
		this.idiomas = idiomas;
	}

	@Override
	public String toString() {
		return "GuiaTuristico [nombreCompleto=" + nombreCompleto + ", idiomas=" + idiomas + ", expHoras=" + expHoras
				+ ", identificacion=" + identificacion + "]";
	}

	/**
	 * Busca y verifica de forma recursiva si un <code>Idioma</code> esta dentro del
	 * 
	 * @param idioma
	 * @param i
	 * @return
	 * @author ElJuancho
	 */
	private boolean verificarIdiomaAux(Idioma idioma, int i) {
		if (idiomas.get(0) == idioma)
			return true;
		if (idiomas.size() == i)
			return false;
		return verificarIdiomaAux(idioma, ++i);
	}

	/**
	 * Verifica si existe un <code>Idioma</code> dentro de la lista. Retorna un
	 * valor booleano dependiendo de la busqueda.
	 * 
	 * @param idioma
	 * @return
	 * @author ElJuancho
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
	 * Agrega un <code>Idioma</code> a la lista. Lanza una
	 * <code>IdiomaYaExistenteException</code> si el <code>Idioma</code> ya existe
	 * en la lista.
	 * 
	 * @param idioma
	 * @throws IdiomaYaExistenteException
	 * @author ElJuancho
	 */
	public void agregarIdioma(Idioma idioma) throws IdiomaYaExistenteException {
		throwIdiomaYaExistente(idioma);
		idiomas.add(idioma);
	}

	/**
	 * Elimina un <code>Idioma</code> de la lista. Lanza una
	 * <code>IdiomaNoExistenteException</code> si el <code>Idioma</code> no existe
	 * en la lista.
	 * 
	 * @param idioma
	 * @throws IdiomaNoExistenteException
	 * @author ElJuancho
	 */
	public void eliminarIdioma(Idioma idioma) throws IdiomaNoExistenteException {
		throwIdiomaNoExistente(idioma);
		idiomas.remove(idioma);
	}

}
