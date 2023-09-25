/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.uniquindio.agenciaViajes.model.GuiaTuristico;
import co.edu.uniquindio.agenciaViajes.services.UtilsJPA;

/**
 * 
 * @author ElJuancho
 */
public class GuiaDao {
	private EntityManager em;
	
	/**
	 * 
	 * @author ElJuancho
	 */
	public GuiaDao() {
		em = UtilsJPA.getEntityManager();
	}
	
	/**
	 * Guarda una entidad GuiaTuristico en la unidad de persistencia.
	 * @param guia
	 * @author ElJuancho
	 */
	public void crearGuia(GuiaTuristico guia) {
		em.getTransaction().begin();
		em.persist(guia);
		em.getTransaction().commit();
	}
	
	/**
	 * busca una entidad GuiaTuristico en la unidad de persistencia.
	 * @param id
	 * @return
	 * @author ElJuancho
	 */
	public GuiaTuristico buscarGuia(Long id) {
		return em.find(GuiaTuristico.class, id);
	}
	
	/**
	 * Elimina una entidad GuiaTuristico de la unidad de persistencia.
	 * @param id
	 * @author ElJuancho
	 */
	public void eliminarGuia(Long id) {
		var aux = buscarGuia(id);
		em.getTransaction().begin();
		em.remove(aux);
		em.getTransaction().commit();
	}
	
	/**
	 * Actualiza una entidad GuiaTuristico de la unidad de persistencia.
	 * @param guia
	 * @author ElJuancho
	 */
	public void actualizarGuia(GuiaTuristico guia) {
		em.getTransaction().begin();
		em.merge(guia);
		em.getTransaction().commit();
	}
	
	/**
	 * Obtiene toda la lista de entidades GuiaTuristico de la unidad de persistencia. Retorna en una List;
	 * @return
	 * @author ElJuancho
	 */
	public List<GuiaTuristico> getListaGuias() {
		return em.createQuery("SELECT g FROM GuiaTuristico g", GuiaTuristico.class).getResultList();
	}
}
