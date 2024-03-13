package co.edu.uniquindio.agenciaviajes.services;

public class DataManagerImpl {
	private static DataManagerImpl instance;

	public static DataManagerImpl getInstance() {
		if (instance == null)
			instance = new DataManagerImpl();
		return instance;
	}

}
