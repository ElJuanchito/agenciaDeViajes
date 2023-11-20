package co.edu.uniquindio.agenciaserver.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.uniquindio.agenciaserver.utils.UtilsJPA;
import co.edu.uniquindio.agenciaviajes.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.ClienteYaExistenteException;
import co.edu.uniquindio.agenciaviajes.model.Cliente;

public class ClienteDao {
	private EntityManager em;
	
	private static ClienteDao instancia;
	
	public static ClienteDao getInstancia() {
		if(instancia == null) instancia = new ClienteDao();
		return instancia;
	}

	private ClienteDao() {
		em = UtilsJPA.getEntityManager();
	}

	public void guardar(Cliente cliente) throws ClienteYaExistenteException {
		if (verificar(cliente.getIdentificacion()))
			throw new ClienteYaExistenteException(
					String.format("El cliente con id %s ya existe en la base de datos", cliente.getIdentificacion()));
		em.persist(cliente);
	}

	public List<Cliente> listar() {
		return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
	}

	public void actualizar(Cliente cliente) throws ClienteNoExistenteException {
		if(!verificar(cliente.getIdentificacion())) throw new ClienteNoExistenteException(String.format("El cliente con id %s no existe, por lo tanto no se puede actualizar", cliente.getIdentificacion()));
		em.getTransaction().begin();
		em.merge(cliente);
		em.getTransaction().commit();
	}

	public Cliente buscar(String id) throws ClienteNoExistenteException {
		Cliente cliente = em.find(Cliente.class, id);
		if(cliente == null) throw new ClienteNoExistenteException("El cliente no existe, por lo tanto no se puede encontrar");
		return cliente;
	}

	public void eliminar(String id) throws ClienteNoExistenteException {
		if(!verificar(id)) throw new ClienteNoExistenteException(String.format("El cliente con id %s no existe, por lo tanto no se puede eliminar", id));
		em.getTransaction().begin();
		em.remove(buscar(id));
		em.getTransaction().commit();
	}

	public boolean verificar(String id) {
		try {
			return buscar(id) != null;
		} catch (ClienteNoExistenteException e) {
			return false;
		}
	}
}
