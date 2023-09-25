package co.edu.uniquindio.agenciaViajes.tests;

import java.util.List;

import org.junit.Test;

import co.edu.uniquindio.agenciaViajes.dao.ClienteDao;
import co.edu.uniquindio.agenciaViajes.model.Cliente;

public class PruebaGuardado {

	@Test
	public void testGuardado() {

		ClienteDao dao = new ClienteDao();

		Cliente a = new Cliente(1L, "Juan", "perdomocardenas18@gmail.com", "3225179118", "Casa de perdomo");
		Cliente e = new Cliente(2L, "Alejo", "breynersanchezquintero@gmail.com", "3006123593", "Montenegro");
		Cliente i = new Cliente(3L, "Circa", "circaman2004@gmail.com", "telefono de circa", "Casa de circa");
		Cliente o = new Cliente(4L, "Yande", "yandelo@gmail.com", "telefono de yande", "Casa de yande");
		Cliente u = new Cliente(5L, "Amador", "arroa03@gmail.com", "telefono de amador", "Casa de amador");

		dao.agregarCliente(a);
		dao.agregarCliente(e);
		dao.agregarCliente(i);
		dao.agregarCliente(o);
		dao.agregarCliente(u);

		List<Cliente> lista = dao.getClientes();
		System.out.println(lista);

		/*
		 * EmpleadoDao dao = new EmpleadoDao();
		 * 
		 * Empleado e = new Empleado("Jose");
		 * 
		 * dao.crearEmpleado(e);
		 */

		/*
		 * List<Idioma> idiomas = new ArrayList<Idioma>(); idiomas.add(Idioma.ESPANOL);
		 * idiomas.add(Idioma.INGLES);
		 * 
		 * 
		 * GuiaTuristico guia = new GuiaTuristico(1L, "El pana que guia", 2, idiomas);
		 * 
		 * var gDao = new GuiaDao();
		 * 
		 * gDao.crearGuia(guia);
		 * 
		 * List<GuiaTuristico> gList = gDao.obtenerListaGuias();
		 * 
		 * System.out.println(gList);
		 */
		// var destinito = new Destino("Peru", "lima", null, null)

	}
}
