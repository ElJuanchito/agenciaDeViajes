package co.edu.uniquindio.agenciaviajes.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.uniquindio.agenciaviajes.model.Cliente;
import co.edu.uniquindio.agenciaviajes.services.UtilsJPA;

/**
 * 
 * 
 * @author ElJuancho
 */
public class ClienteDao {

    private final EntityManager em;

    public ClienteDao() {
        em = UtilsJPA.getEntityManager();
    }

    public void agregarCliente(Cliente cliente) {
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }

    public Cliente obtenerCliente(int id) {
        return em.find(Cliente.class, id);
    }

    public List<Cliente> getClientes() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }
    
    public List<Cliente> obtenerPorBusquedaEspecifica(String jpql){
    	return em.createQuery(jpql, Cliente.class).getResultList();
    }

    public void actualizarCliente(Cliente cliente) {
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
    }

    public void eliminarCliente(int id) {
        Cliente cliente = em.find(Cliente.class, id);
        if (cliente != null) {
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
        }
    }

    public void cerrar() {
        if (em != null) {
            em.close();
        }
    }
}
