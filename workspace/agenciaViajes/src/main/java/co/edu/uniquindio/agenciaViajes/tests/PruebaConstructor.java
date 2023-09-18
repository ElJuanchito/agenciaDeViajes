/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaViajes.tests;

import co.edu.uniquindio.agenciaViajes.dao.ClienteDao;
import co.edu.uniquindio.agenciaViajes.model.Cliente;

/**
 * 
 * @author ElJuancho
 */
public class PruebaConstructor {
	public static void main(String[] args) {
		Cliente cliente1 = Cliente.builder().identificacion(100L).nombreCompleto("DrBayter").email("drBayter@gmail.com").telefono("3113214543").direccion("Casa del drBayter").build();
		
		ClienteDao cDao = new ClienteDao();
		
		cDao.agregarCliente(cliente1);
		cDao.cerrar();
	}
}
