package co.edu.uniquindio.agenciaserver.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

import co.edu.uniquindio.agenciaserver.exceptions.DestinoNoExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.DestinoYaExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.PaqueteNoExistenteException;
import co.edu.uniquindio.agenciaserver.utils.MathUtils;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "paquetes")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Paquete implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	private List<Imagen> getDestinosImages(List<Imagen> imagenes,int i){
		if(i==destinos.size()) 
			return imagenes ;
		imagenes.add(destinos.get(i).getImagenes().get(0));
		return getDestinosImages(imagenes, i+1);
		
	}
	public List<Imagen> listarImagenesDestino(){
		List<Imagen> imagenes= new ArrayList<Imagen>();
		return getDestinosImages(imagenes, 0);
		
	}
	
	private double getAcumPromedios(double promedio,int i) {
		if(i==destinos.size())
			return promedio;
		return getAcumPromedios(promedio + destinos.get(i).getPromedio(), i+1);
	}
	
	public double getPromedioDestinos() {
		double prom=5;
		if(destinos.size()!=0) {
			prom= (getAcumPromedios(0, 0))/destinos.size();
		}
		return MathUtils.round(prom,1);
		
	}

}
