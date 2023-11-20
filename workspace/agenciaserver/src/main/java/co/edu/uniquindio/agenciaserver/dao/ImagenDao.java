package co.edu.uniquindio.agenciaserver.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.uniquindio.agenciaserver.utils.UtilsJPA;
import co.edu.uniquindio.agenciaviajes.exceptions.ImagenNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.ImagenYaExistenteException;
import co.edu.uniquindio.agenciaviajes.model.Imagen;

public class ImagenDao {
	private EntityManager em;
	private static ImagenDao instancia;
	
	public static ImagenDao getInstancia() {
		if(instancia == null) instancia = new ImagenDao();
		return instancia;
	}

	public ImagenDao() {
		em = UtilsJPA.getEntityManager();
	}

	public void guardar(Imagen imagen) throws ImagenYaExistenteException {
		if (verificar(imagen.getId()))
			throw new ImagenYaExistenteException(
					String.format("La imagen con id %s ya existe en la base de datos", imagen.getId()));
		em.persist(imagen);
	}

	public List<Imagen> listar() {
		return em.createQuery("SELECT i FROM Imagen i", Imagen.class).getResultList();
	}

	public void actualizar(Imagen imagen) throws ImagenNoExistenteException {
		if (!verificar(imagen.getId()))
			throw new ImagenNoExistenteException(String
					.format("La imagen con id %s no existe, por lo tanto no se puede actualizar", imagen.getId()));
		em.getTransaction().begin();
		em.merge(imagen);
		em.getTransaction().commit();
	}

	public Imagen buscar(Long id) throws ImagenNoExistenteException {
		Imagen imagen = em.find(Imagen.class, id);
		if (imagen == null)
			throw new ImagenNoExistenteException("La imagen no existe, por lo tanto no se puede encontrar");
		return imagen;
	}

	public void eliminar(Long id) throws ImagenNoExistenteException {
		if (!verificar(id))
			throw new ImagenNoExistenteException("La imagen con id %s no existe, por lo tanto no se puede eliminar");
		em.getTransaction().begin();
		em.remove(buscar(id));
		em.getTransaction().commit();
	}

	public boolean verificar(Long id) {
		try {
			return buscar(id) != null;
		} catch (ImagenNoExistenteException e) {
			return false;
		}
	}
}
