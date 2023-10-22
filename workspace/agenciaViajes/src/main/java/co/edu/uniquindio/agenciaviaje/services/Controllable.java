package co.edu.uniquindio.agenciaviaje.services;

public interface Controllable extends DataControllable<Void> {

	@Override
	default void inicializarDatos(Void dato) {
	}

}
