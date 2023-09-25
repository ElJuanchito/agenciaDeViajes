/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.uniquindio.agenciaViajes.model.Destino;
import co.edu.uniquindio.agenciaViajes.services.UtilsJPA;

/**
 * 
 * @author ElJuancho
 */
public class DestinoDao {
	private final EntityManager em;
	
	/**
	 * 
	 * @author ElJuancho
	 */
	public DestinoDao() {
		em = UtilsJPA.getEntityManager();
	}
	
	public void crearDestino(Destino destino) {
		em.getTransaction().begin();
		em.persist(destino);
		em.getTransaction().commit();
	}
	
	public Destino buscarDestino(Long id) {
		return em.find(Destino.class, id);
	}
	
	public void actualizarDestino(Destino destino) {
		em.getTransaction().begin();
		em.merge(destino);
		em.getTransaction().commit();
	}
	
	public void eliminarDestino(Long id) {
		em.remove(id);
	}
	
	public List<Destino> getListaDestinos(){
		return em.createQuery("SELECT d FROM Destino d", Destino.class).getResultList();
	}
}
