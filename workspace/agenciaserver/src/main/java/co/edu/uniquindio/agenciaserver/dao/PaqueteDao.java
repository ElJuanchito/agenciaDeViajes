package co.edu.uniquindio.agenciaserver.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.uniquindio.agenciaserver.exceptions.PaqueteNoExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.PaqueteYaExistenteException;
import co.edu.uniquindio.agenciaserver.model.Paquete;
import co.edu.uniquindio.agenciaserver.utils.UtilsJPA;

public class PaqueteDao {
	private EntityManager em;
	private static PaqueteDao instancia;
	
	public static PaqueteDao getInstancia() {
		if(instancia == null) instancia = new PaqueteDao();
		return instancia;
	}

	public PaqueteDao() {
		em = UtilsJPA.getEntityManager();
	}

	public void guardar(Paquete paquete) throws PaqueteYaExistenteException {
		if (verificar(paquete.getId()))
			throw new PaqueteYaExistenteException(
					String.format("El paquete con id %s ya existe en la base de datos", paquete.getId()));
		em.persist(paquete);
	}

	public List<Paquete> listar() {
		return em.createQuery("SELECT p FROM Paquete p", Paquete.class).getResultList();
	}

	public void actualizar(Paquete paquete) throws PaqueteNoExistenteException {
		if(!verificar(paquete.getId())) throw new PaqueteNoExistenteException(String.format("El paquete con id %s no existe, por lo tanto no se puede actualizar", paquete.getId()));
		em.getTransaction().begin();
		em.merge(paquete);
		em.getTransaction().commit();
	}

	public Paquete buscar(Long id) throws PaqueteNoExistenteException {
		Paquete paquete = em.find(Paquete.class, id);
		if(paquete == null) throw new PaqueteNoExistenteException("El paquete no existe, por lo tanto no se puede encontrar");
		return paquete;
	}

	public void eliminar(Long id) throws PaqueteNoExistenteException {
		if(!verificar(id)) throw new PaqueteNoExistenteException("El paquete con id %s no existe, por lo tanto no se puede eliminar");
		em.getTransaction().begin();
		em.remove(buscar(id));
		em.getTransaction().commit();
	}

	public boolean verificar(Long id) {
		try {
			return buscar(id) != null;
		} catch (PaqueteNoExistenteException e) {
			return false;
		}
	}

}
