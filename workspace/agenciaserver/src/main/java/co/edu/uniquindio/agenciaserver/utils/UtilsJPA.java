package co.edu.uniquindio.agenciaserver.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UtilsJPA {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public static void closeConnection() {
		emf.close();
	}
}
