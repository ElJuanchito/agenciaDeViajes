package co.edu.uniquindio.agenciaserver.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.uniquindio.agenciaserver.utils.UtilsJPA;
import co.edu.uniquindio.agenciaviajes.exceptions.GuiaNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.GuiaYaExistenteException;
import co.edu.uniquindio.agenciaviajes.model.GuiaTuristico;

public class GuiaDao {
	private EntityManager em;
	
	private static GuiaDao instancia;
	
	public static GuiaDao getInstancia() {
		if(instancia == null) instancia = new GuiaDao();
		return instancia;
	}

	public GuiaDao() {
		em = UtilsJPA.getEntityManager();
	}

	public void guardar(GuiaTuristico guia) throws GuiaYaExistenteException {
		if (verificar(guia.getIdentificacion()))
			throw new GuiaYaExistenteException(
					String.format("El guia con id %s ya existe en la base de datos", guia.getIdentificacion()));
		em.persist(guia);
	}

	public List<GuiaTuristico> listar() {
		return em.createQuery("SELECT g FROM GuiaTuristico g", GuiaTuristico.class).getResultList();
	}

	public void actualizar(GuiaTuristico guia) throws GuiaNoExistenteException {
		if(!verificar(guia.getIdentificacion())) throw new GuiaNoExistenteException(String.format("El guia con id %s no existe, por lo tanto no se puede actualizar", guia.getIdentificacion()));
		em.getTransaction().begin();
		em.merge(guia);
		em.getTransaction().commit();
	}

	public GuiaTuristico buscar(String id) throws GuiaNoExistenteException {
		GuiaTuristico guia = em.find(GuiaTuristico.class, id);
		if(guia == null) throw new GuiaNoExistenteException("El guia no existe, por lo tanto no se puede encontrar");
		return guia;
	}

	public void eliminar(String id) throws GuiaNoExistenteException {
		if(!verificar(id)) throw new GuiaNoExistenteException("El guia con id %s no existe, por lo tanto no se puede eliminar");
		em.getTransaction().begin();
		em.remove(buscar(id));
		em.getTransaction().commit();
	}

	public boolean verificar(String id) {
		try {
			return buscar(id) != null;
		} catch (GuiaNoExistenteException e) {
			return false;
		}
	}
}
