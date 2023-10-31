package co.edu.uniquindio.agenciaviajes.tests;

import org.junit.Test;

import co.edu.uniquindio.agenciaviajes.dao.ClienteDao;
import co.edu.uniquindio.agenciaviajes.model.Cliente;

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
