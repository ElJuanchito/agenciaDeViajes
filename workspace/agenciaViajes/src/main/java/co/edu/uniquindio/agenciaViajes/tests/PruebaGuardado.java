package co.edu.uniquindio.agenciaViajes.tests;

import co.edu.uniquindio.agenciaViajes.Dao.EmpleadoDao;
import co.edu.uniquindio.agenciaViajes.model.Empleado;

public class PruebaGuardado {

	public static void main(String[] args) {
		EmpleadoDao dao = new EmpleadoDao();
		
		Empleado e = new Empleado("Steven");
		
		dao.crearEmpleado(e);
		
		System.out.println(dao.obtenerEmpleadoPorId(1L));
	}
}
