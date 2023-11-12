package co.edu.uniquindio.agenciaserver.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.uniquindio.agenciaserver.exceptions.ReservaNoExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.ReservaYaExistenteException;
import co.edu.uniquindio.agenciaserver.model.Reserva;
import co.edu.uniquindio.agenciaserver.utils.UtilsJPA;

public class ReservaDao {
	private EntityManager em;
	private static ReservaDao instancia;
	
	public static ReservaDao getInstancia() {
		if(instancia == null) instancia = new ReservaDao();
		return instancia;
	}

	public ReservaDao() {
		em = UtilsJPA.getEntityManager();
	}

	public void guardar(Reserva reserva) throws ReservaYaExistenteException {
		if (verificar(reserva.getId()))
			throw new ReservaYaExistenteException(
					String.format("La reserva con id %s ya existe en la base de datos", reserva.getId()));
		em.persist(reserva);
	}

	public List<Reserva> listar() {
		return em.createQuery("SELECT r FROM Reserva r", Reserva.class).getResultList();
	}

	public void actualizar(Reserva reserva) throws ReservaNoExistenteException {
		if(!verificar(reserva.getId())) throw new ReservaNoExistenteException(String.format("La reserva con id %s no existe, por lo tanto no se puede actualizar", reserva.getId()));
		em.getTransaction().begin();
		em.merge(reserva);
		em.getTransaction().commit();
	}

	public Reserva buscar(Long id) throws ReservaNoExistenteException {
		Reserva reserva = em.find(Reserva.class, id);
		if(reserva == null) throw new ReservaNoExistenteException("La reserva no existe, por lo tanto no se puede encontrar");
		return reserva;
	}

	public void eliminar(Long id) throws ReservaNoExistenteException {
		if(!verificar(id)) throw new ReservaNoExistenteException("La reserva con id %s no existe, por lo tanto no se puede eliminar");
		em.getTransaction().begin();
		em.remove(buscar(id));
		em.getTransaction().commit();
	}

	public boolean verificar(Long id) {
		try {
			return buscar(id) != null;
		} catch (ReservaNoExistenteException e) {
			return false;
		}
	}
}
