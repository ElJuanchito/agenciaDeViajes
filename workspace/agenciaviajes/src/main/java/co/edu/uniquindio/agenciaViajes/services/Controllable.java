package co.edu.uniquindio.agenciaViajes.services;

public interface Controllable extends DataControllable<Void> {

	@Override
	default void inicializarDatos(Void dato) {
	}

}
