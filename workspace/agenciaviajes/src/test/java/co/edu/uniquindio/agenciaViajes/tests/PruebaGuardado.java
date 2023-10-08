package co.edu.uniquindio.agenciaViajes.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import co.edu.uniquindio.agenciaViajes.dao.ClienteDao;
import co.edu.uniquindio.agenciaViajes.dao.GuiaDao;
import co.edu.uniquindio.agenciaViajes.model.Cliente;
import co.edu.uniquindio.agenciaViajes.model.GuiaTuristico;
import co.edu.uniquindio.agenciaViajes.model.Idioma;

public class PruebaGuardado {

	@Test
	public void testGuardado() {

		ClienteDao dao = new ClienteDao();

		Cliente a = Cliente.builder().identificacion("1").nombreCompleto("Juan").email("perdomocardenas18@gmail.com")
				.telefono("3225179118").direccion("Casa de perdomo").build();
		Cliente e = Cliente.builder().identificacion("2").nombreCompleto("Alejo")
				.email("breynersanchezquintero@gmail.com").telefono("3006123593").direccion("Montenegro").build();
		Cliente i = Cliente.builder().identificacion("3").nombreCompleto("Circa").email("circaman2004@gmail.com")
				.telefono("telefono de circa").direccion("Casa de circa").build();
		Cliente o = Cliente.builder().identificacion("4").nombreCompleto("Yande").email("yandelo@gmail.com")
				.telefono("telefono de yande").direccion("Casa de yande").build();
		Cliente u = Cliente.builder().identificacion("5").nombreCompleto("Amador").email("arroa03@gmail.com")
				.telefono("telefono de amador").direccion("Casa de amador").build();
		Cliente aa = Cliente.builder().identificacion("6").nombreCompleto("Quintero")
				.email("santiquinterouribe0412@gmail.com").telefono("telefono de santi").direccion("Casa de el santi")
				.build();

		dao.agregarCliente(a);
		dao.agregarCliente(e);
		dao.agregarCliente(i);
		dao.agregarCliente(o);
		dao.agregarCliente(u);
		dao.agregarCliente(aa);

		List<Cliente> lista = dao.getClientes();
		System.out.println(lista);

	}

	@Test
	public void pruebaGuia() {
		List<Idioma> idiomas = new ArrayList<Idioma>();
		idiomas.add(Idioma.ESPANOL);
		idiomas.add(Idioma.INGLES);

		GuiaTuristico guia = GuiaTuristico.builder().identificacion("1").nombreCompleto("El pana que guia").expHoras(2)
				.idiomas((Idioma[]) idiomas.toArray()).build();

		var gDao = new GuiaDao();

		gDao.crearGuia(guia);

		List<GuiaTuristico> gList = gDao.getListaGuias();
		System.out.println(gList);
	}
}
