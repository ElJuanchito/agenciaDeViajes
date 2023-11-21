package co.edu.uniquindio.agenciaserver.services;

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

public class PersistenciaDataManager implements DataManager{
	
	private static final String RUTAADMINISTRADOR="src/main/resources/co/edu/uniquindio/agenciaserver/data/administrador.dat";
	private static final String RUTACLIENTES="src/main/resources/co/edu/uniquindio/agenciaserver/data/clientes.dat";
	private static final String RUTAGUIAS="src/main/resources/co/edu/uniquindio/agenciaserver/data/guias.dat";
	private static final String RUTAPAQUETES="src/main/resources/co/edu/uniquindio/agenciaserver/data/paquetes.dat";
	private static final String RUTARESERVAS="src/main/resources/co/edu/uniquindio/agenciaserver/data/reservas.dat";

	@Override
	public void guardarCliente(Cliente cliente) throws ClienteYaExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cliente> listarCliente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarCliente(Cliente cliente) throws ClienteNoExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCliente(String id) throws ClienteNoExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente buscarCliente(String id) throws ClienteNoExistenteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verificarCliente(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void guardarDestino(Destino destino) throws DestinoYaExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Destino> listarDestino() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarDestino(Destino destino) throws DestinoNoExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarDestino(Long id) throws DestinoNoExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Destino buscarDestino(Long id) throws DestinoNoExistenteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verificarDestino(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void guardarGuia(GuiaTuristico guia) throws GuiaYaExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GuiaTuristico> listarGuia() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarGuia(GuiaTuristico guia) throws GuiaNoExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarGuia(String id) throws GuiaNoExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GuiaTuristico buscarGuia(String id) throws GuiaNoExistenteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verificarGuia(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void guardarImagen(Imagen imagen) throws ImagenYaExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Imagen> listarImagen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarImagen(Imagen guia) throws ImagenNoExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarImagen(Long id) throws ImagenNoExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Imagen buscarImagen(Long id) throws ImagenNoExistenteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verificarImagen(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void guardarPaquete(Paquete paquete) throws PaqueteYaExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Paquete> listarPaquete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarPaquete(Paquete paquete) throws PaqueteNoExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarPaquete(Long id) throws PaqueteNoExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Paquete buscarPaquete(Long id) throws PaqueteNoExistenteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verificarPaquete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void guardarReserva(Reserva reserva) throws ReservaYaExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reserva> listarReserva() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarReserva(Reserva reserva) throws ReservaNoExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarReserva(Long id) throws ReservaNoExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reserva buscarReserva(Long id) throws ReservaNoExistenteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verificarReserva(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void guardarAdministrador(Administrador admin) throws AdministradorYaExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Administrador> listarAdmin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarAdministrador(Administrador admin) throws AdministradorNoExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarAdministrador(String id) throws AdministradorNoExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Administrador buscarAdministrador(String id) throws AdministradorNoExistenteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verificarAdministrador(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hacerLogin(Loginable user) throws UsuarioNoExistenteException {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
