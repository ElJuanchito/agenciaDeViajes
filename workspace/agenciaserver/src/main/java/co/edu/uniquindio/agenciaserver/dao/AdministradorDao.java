package co.edu.uniquindio.agenciaserver.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.uniquindio.agenciaserver.utils.UtilsJPA;
import co.edu.uniquindio.agenciaviajes.exceptions.AdministradorNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.AdministradorYaExistenteException;
import co.edu.uniquindio.agenciaviajes.model.Administrador;

public class AdministradorDao {
private EntityManager em;
	
	private static AdministradorDao instancia;
	
	public static AdministradorDao getInstancia() {
		if(instancia == null) instancia = new AdministradorDao();
		return instancia;
	}

	private AdministradorDao() {
		em = UtilsJPA.getEntityManager();
	}

	public void guardar(Administrador admin) throws AdministradorYaExistenteException {
		if (verificar(admin.getIdentificacion()))
			throw new AdministradorYaExistenteException(
					String.format("El admin con id %s ya existe en la base de datos", admin.getIdentificacion()));
		em.persist(admin);
	}

	public List<Administrador> listar() {
		return em.createQuery("SELECT a FROM Administrador a", Administrador.class).getResultList();
	}

	public void actualizar(Administrador admin) throws AdministradorNoExistenteException {
		if(!verificar(admin.getIdentificacion())) throw new AdministradorNoExistenteException(String.format("El admin con id %s no existe, por lo tanto no se puede actualizar", admin.getIdentificacion()));
		em.getTransaction().begin();
		em.merge(admin);
		em.getTransaction().commit();
	}

	public Administrador buscar(String id) throws AdministradorNoExistenteException {
		Administrador admin = em.find(Administrador.class, id);
		if(admin == null) throw new AdministradorNoExistenteException("El admin no existe, por lo tanto no se puede encontrar");
		return admin;
	}

	public void eliminar(String id) throws AdministradorNoExistenteException {
		if(!verificar(id)) throw new AdministradorNoExistenteException(String.format("El cliente con id %s no existe, por lo tanto no se puede eliminar", id));
		em.getTransaction().begin();
		em.remove(buscar(id));
		em.getTransaction().commit();
	}

	public boolean verificar(String id) {
		try {
			return buscar(id) != null;
		} catch (AdministradorNoExistenteException e) {
			return false;
		}
	}
}
