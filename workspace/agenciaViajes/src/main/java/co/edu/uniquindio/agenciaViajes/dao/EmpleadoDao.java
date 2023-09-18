package co.edu.uniquindio.agenciaViajes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.edu.uniquindio.agenciaViajes.model.Empleado;

/**
 * 
 * 
 * @author ElJuancho
 */
public class EmpleadoDao {

    private EntityManagerFactory entityManagerFactory;

    public EmpleadoDao() {
        // Inicializar el EntityManagerFactory con el nombre de tu unidad de persistencia
        entityManagerFactory = Persistence.createEntityManagerFactory("aplicacion");
    }

    public void crearEmpleado(Empleado empleado) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(empleado);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Empleado obtenerEmpleadoPorId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Empleado empleado = entityManager.find(Empleado.class, id);
        entityManager.close();
        return empleado;
    }

    public List<Empleado> obtenerTodosLosEmpleados() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Empleado> empleados = entityManager.createQuery("SELECT e FROM Empleado e", Empleado.class)
                .getResultList();
        entityManager.close();
        return empleados;
    }

    public void actualizarEmpleado(Empleado empleado) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.merge(empleado);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void eliminarEmpleado(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Empleado empleado = entityManager.find(Empleado.class, id);
        if (empleado != null) {
            entityManager.remove(empleado);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void cerrar() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
