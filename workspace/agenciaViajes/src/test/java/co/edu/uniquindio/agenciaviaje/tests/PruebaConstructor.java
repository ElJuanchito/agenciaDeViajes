package co.edu.uniquindio.agenciaviaje.tests;

import org.junit.Test;

import co.edu.uniquindio.agenciaviaje.dao.ClienteDao;
import co.edu.uniquindio.agenciaviaje.model.Cliente;

public class PruebaConstructor {
	@Test
	public void builderTest() {
		Cliente cliente1 = Cliente.builder().identificacion("100").nombreCompleto("DrBayter").email("drBayter@gmail.com")
				.telefono("3113214543").direccion("Casa del drBayter").build();

		ClienteDao cDao = new ClienteDao();

		cDao.agregarCliente(cliente1);
		cDao.cerrar();
	}
}
