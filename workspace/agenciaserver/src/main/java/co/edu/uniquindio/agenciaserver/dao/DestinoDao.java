package co.edu.uniquindio.agenciaserver.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.uniquindio.agenciaserver.utils.UtilsJPA;
import co.edu.uniquindio.agenciaviajes.exceptions.DestinoNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.DestinoYaExistenteException;
import co.edu.uniquindio.agenciaviajes.model.Destino;

public class DestinoDao {
	private static DestinoDao instancia;
	private EntityManager em;
	
	
	
	public static DestinoDao getInstancia() {
		if(instancia == null) instancia = new DestinoDao();
		return instancia;
	}

	public DestinoDao() {
		em = UtilsJPA.getEntityManager();
	}

	public void guardar(Destino destino) throws DestinoYaExistenteException {
		if (verificar(destino.getId()))
			throw new DestinoYaExistenteException(
					String.format("El destino con id %s ya existe en la base de datos", destino.getId()));
		em.getTransaction().begin();
		em.persist(destino);
		em.getTransaction().commit();
		
	}

	public List<Destino> listar() {
		return em.createQuery("SELECT d FROM Destino d", Destino.class).getResultList();
	}

	public void actualizar(Destino destino) throws DestinoNoExistenteException {
		if(!verificar(destino.getId())) throw new DestinoNoExistenteException(String.format("El destino con id %s no existe, por lo tanto no se puede actualizar", destino.getId()));
		em.getTransaction().begin();
		em.merge(destino);
		em.getTransaction().commit();
	}

	public Destino buscar(Long id) throws DestinoNoExistenteException {
		Destino destino = em.find(Destino.class, id);
		if(destino == null) throw new DestinoNoExistenteException("El destino no existe, por lo tanto no se puede encontrar");
		return destino;
	}

	public void eliminar(Long id) throws DestinoNoExistenteException {
		if(!verificar(id)) throw new DestinoNoExistenteException("El destino con id %s no existe, por lo tanto no se puede eliminar");
		em.getTransaction().begin();
		em.remove(buscar(id));
		em.getTransaction().commit();
	}

	public boolean verificar(Long id) {
		try {
			return buscar(id) != null;
		} catch (DestinoNoExistenteException e) {
			return false;
		}
	}
}
