package co.edu.uniquindio.agenciaviajes.services;

import java.util.List;

import co.edu.uniquindio.agenciaviajes.exceptions.AdministradorNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.AdministradorYaExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.ClienteYaExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.DestinoNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.DestinoYaExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.GuiaNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.GuiaYaExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.ImagenNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.ImagenYaExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.PaqueteNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.PaqueteYaExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.ReservaNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.ReservaYaExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.UsuarioNoExistenteException;
import co.edu.uniquindio.agenciaviajes.model.Administrador;
import co.edu.uniquindio.agenciaviajes.model.Cliente;
import co.edu.uniquindio.agenciaviajes.model.Destino;
import co.edu.uniquindio.agenciaviajes.model.GuiaTuristico;
import co.edu.uniquindio.agenciaviajes.model.Imagen;
import co.edu.uniquindio.agenciaviajes.model.Loginable;
import co.edu.uniquindio.agenciaviajes.model.Paquete;
import co.edu.uniquindio.agenciaviajes.model.Reserva;

public interface DataManager {

	default void guardarCliente(Cliente cliente) throws ClienteYaExistenteException {
		if (verificarCliente(cliente.getIdentificacion()))
			throw new ClienteYaExistenteException("El cliente ya existe, no se puede agregar");
	}

	List<Cliente> listarCliente();

	void actualizarCliente(Cliente cliente) throws ClienteNoExistenteException;

	void eliminarCliente(String id) throws ClienteNoExistenteException;

	Cliente buscarCliente(String id) throws ClienteNoExistenteException;

	boolean verificarCliente(String id);

	void guardarDestino(Destino destino) throws DestinoYaExistenteException;

	List<Destino> listarDestino();

	void actualizarDestino(Destino destino) throws DestinoNoExistenteException;

	void eliminarDestino(Long id) throws DestinoNoExistenteException;

	Destino buscarDestino(Long id) throws DestinoNoExistenteException;

	boolean verificarDestino(Long id);

	void guardarGuia(GuiaTuristico guia) throws GuiaYaExistenteException;

	List<GuiaTuristico> listarGuia();

	void actualizarGuia(GuiaTuristico guia) throws GuiaNoExistenteException;

	void eliminarGuia(String id) throws GuiaNoExistenteException;

	GuiaTuristico buscarGuia(String id) throws GuiaNoExistenteException;

	boolean verificarGuia(String id);

	void guardarImagen(Imagen imagen) throws ImagenYaExistenteException;

	List<Imagen> listarImagen();

	void actualizarImagen(Imagen guia) throws ImagenNoExistenteException;

	void eliminarImagen(Long id) throws ImagenNoExistenteException;

	Imagen buscarImagen(Long id) throws ImagenNoExistenteException;

	boolean verificarImagen(Long id);

	void guardarPaquete(Paquete paquete) throws PaqueteYaExistenteException;

	List<Paquete> listarPaquete();

	void actualizarPaquete(Paquete paquete) throws PaqueteNoExistenteException;

	void eliminarPaquete(Long id) throws PaqueteNoExistenteException;

	Paquete buscarPaquete(Long id) throws PaqueteNoExistenteException;

	boolean verificarPaquete(Long id);

	void guardarReserva(Reserva reserva) throws ReservaYaExistenteException;

	List<Reserva> listarReserva();

	void actualizarReserva(Reserva reserva) throws ReservaNoExistenteException;

	void eliminarReserva(Long id) throws ReservaNoExistenteException;

	Reserva buscarReserva(Long id) throws ReservaNoExistenteException;

	boolean verificarReserva(Long id);

	void guardarAdministrador(Administrador admin) throws AdministradorYaExistenteException;

	List<Administrador> listarAdmin();

	void actualizarAdministrador(Administrador admin) throws AdministradorNoExistenteException;

	void eliminarAdministrador(String id) throws AdministradorNoExistenteException;

	Administrador buscarAdministrador(String id) throws AdministradorNoExistenteException;

	boolean verificarAdministrador(String id);

	boolean hacerLogin(Loginable user) throws UsuarioNoExistenteException;
}
