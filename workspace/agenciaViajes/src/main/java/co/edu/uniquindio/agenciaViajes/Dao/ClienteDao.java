package co.edu.uniquindio.agenciaViajes.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.edu.uniquindio.agenciaViajes.model.Cliente;

public class ClienteDao {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public ClienteDao() {
        emf = Persistence.createEntityManagerFactory("baseDatos"); // Reemplaza "nombre_unidad" con el nombre de tu unidad de persistencia
        em = emf.createEntityManager();
    }

    public void agregarCliente(Cliente cliente) {
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }

    public Cliente obtenerCliente(int id) {
        return em.find(Cliente.class, id);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
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
        if (emf != null) {
            emf.close();
        }
    }
}
