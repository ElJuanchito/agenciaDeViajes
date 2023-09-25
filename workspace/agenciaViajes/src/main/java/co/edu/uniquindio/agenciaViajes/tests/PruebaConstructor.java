package co.edu.uniquindio.agenciaViajes.tests;

import org.junit.Test;

import co.edu.uniquindio.agenciaViajes.dao.ClienteDao;
import co.edu.uniquindio.agenciaViajes.model.Cliente;
import javafx.scene.control.ComboBox;

public class PruebaConstructor {
	@Test
	public void builderTest() {
		Cliente cliente1 = Cliente.builder().identificacion(100L).nombreCompleto("DrBayter").email("drBayter@gmail.com")
				.telefono("3113214543").direccion("Casa del drBayter").build();

		ClienteDao cDao = new ClienteDao();

		cDao.agregarCliente(cliente1);
		cDao.cerrar();
	}
}
