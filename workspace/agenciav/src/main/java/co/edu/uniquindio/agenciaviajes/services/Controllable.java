package co.edu.uniquindio.agenciaviajes.services;

public interface Controllable extends DataControllable<Void> {

	@Override
	default void inicializarDatos(Void dato) {
	}

}
