package prueba.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import prueba.model.Empleado;
import prueba.model.EmpleadoDAO;

public class PruebaGuardado {
	public static EntityManagerFactory emf;
	public static EntityManager em;
	
	public static void main(String[] args) {
		EmpleadoDAO dao = new EmpleadoDAO();
		
		Empleado ep = new Empleado("Jose");
		
		dao.crearEmpleado(ep);
		
		System.out.println(dao.obtenerEmpleadoPorId(1L));
	}
}
