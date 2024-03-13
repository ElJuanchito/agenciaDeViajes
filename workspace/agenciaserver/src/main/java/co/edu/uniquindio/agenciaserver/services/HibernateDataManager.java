package co.edu.uniquindio.agenciaserver.services;

import java.util.List;

import co.edu.uniquindio.agenciaserver.dao.AdministradorDao;
import co.edu.uniquindio.agenciaserver.dao.ClienteDao;
import co.edu.uniquindio.agenciaserver.dao.DestinoDao;
import co.edu.uniquindio.agenciaserver.dao.GuiaDao;
import co.edu.uniquindio.agenciaserver.dao.ImagenDao;
import co.edu.uniquindio.agenciaserver.dao.PaqueteDao;
import co.edu.uniquindio.agenciaserver.dao.ReservaDao;
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
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class HibernateDataManager {

	private static HibernateDataManager instancia;

	public static HibernateDataManager getInstance() {
		if (instancia == null)
			instancia = new HibernateDataManager();
		return instancia;
	}

	public void guardarCliente(Cliente cliente) throws ClienteYaExistenteException {
		ClienteDao.getInstancia().guardar(cliente);
	}

	public List<Cliente> listarCliente() {
		return ClienteDao.getInstancia().listar();
	}

	public void actualizarCliente(Cliente cliente) throws ClienteNoExistenteException {
		ClienteDao.getInstancia().actualizar(cliente);
	}

	public void eliminarCliente(String id) throws ClienteNoExistenteException {
		ClienteDao.getInstancia().eliminar(id);
	}

	public Cliente buscarCliente(String id) {
		try {
			return ClienteDao.getInstancia().buscar(id);
		} catch (ClienteNoExistenteException e) {
			return null;
		}
	}

	public boolean verificarCliente(String id) {
		return ClienteDao.getInstancia().verificar(id);
	}

	public void agregarDestino(Destino destino) throws DestinoYaExistenteException {
		DestinoDao.getInstancia().guardar(destino);
	}

	public List<Destino> listarDestino() {
		return DestinoDao.getInstancia().listar();
	}

	public void actualizarDestino(Destino destino) throws DestinoNoExistenteException {
		DestinoDao.getInstancia().actualizar(destino);
	}

	public void eliminarDestino(Long id) throws DestinoNoExistenteException {
		DestinoDao.getInstancia().eliminar(id);
	}

	public Destino buscarDestino(Long id) throws DestinoNoExistenteException {
		return DestinoDao.getInstancia().buscar(id);
	}

	public boolean verificarDestino(Long id) {
		return DestinoDao.getInstancia().verificar(id);
	}

	public void guardarGuia(GuiaTuristico guia) throws GuiaYaExistenteException {
		GuiaDao.getInstancia().guardar(guia);
	}

	public List<GuiaTuristico> listarGuia() {
		return GuiaDao.getInstancia().listar();
	}

	public void actualizarGuia(GuiaTuristico guia) throws GuiaNoExistenteException {
		GuiaDao.getInstancia().actualizar(guia);
	}

	public void eliminarGuia(String id) throws GuiaNoExistenteException {
		GuiaDao.getInstancia().eliminar(id);
	}

	public GuiaTuristico buscarGuia(String id) throws GuiaNoExistenteException {
		return GuiaDao.getInstancia().buscar(id);
	}

	public boolean verificarGuia(String id) {
		return GuiaDao.getInstancia().verificar(id);
	}

	public void guardarImagen(Imagen imagen) throws ImagenYaExistenteException {
		ImagenDao.getInstancia().guardar(imagen);

	}

	public List<Imagen> listarImagen() {
		return ImagenDao.getInstancia().listar();
	}

	public void actualizarImagen(Imagen imagen) throws ImagenNoExistenteException {
		ImagenDao.getInstancia().actualizar(imagen);
	}

	public void eliminarImagen(Long id) throws ImagenNoExistenteException {
		ImagenDao.getInstancia().eliminar(id);
	}

	public Imagen buscarImagen(Long id) throws ImagenNoExistenteException {
		return ImagenDao.getInstancia().buscar(id);
	}

	public boolean verificarImagen(Long id) {
		return ImagenDao.getInstancia().verificar(id);
	}

	public void guardarPaquete(Paquete paquete) throws PaqueteYaExistenteException {
		PaqueteDao.getInstancia().guardar(paquete);
	}

	public List<Paquete> listarPaquete() {
		return PaqueteDao.getInstancia().listar();
	}

	public void actualizarPaquete(Paquete paquete) throws PaqueteNoExistenteException {
		PaqueteDao.getInstancia().actualizar(paquete);

	}

	public void eliminarPaquete(Long id) throws PaqueteNoExistenteException {
		PaqueteDao.getInstancia().eliminar(id);
	}

	public Paquete buscarPaquete(Long id) throws PaqueteNoExistenteException {
		return PaqueteDao.getInstancia().buscar(id);
	}

	public void guardarReserva(Reserva reserva) throws ReservaYaExistenteException {
		ReservaDao.getInstancia().guardar(reserva);
	}

	public boolean verificarPaquete(Long id) {
		return ReservaDao.getInstancia().verificar(id);
	}

	public List<Reserva> listarReserva() {
		return ReservaDao.getInstancia().listar();
	}

	public void actualizarReserva(Reserva reserva) throws ReservaNoExistenteException {
		ReservaDao.getInstancia().actualizar(reserva);
	}

	public void eliminarReserva(Long id) throws ReservaNoExistenteException {
		ReservaDao.getInstancia().eliminar(id);
	}

	public Reserva buscarReserva(Long id) throws ReservaNoExistenteException {
		return ReservaDao.getInstancia().buscar(id);
	}

	public boolean verificarReserva(Long id) {
		return ReservaDao.getInstancia().verificar(id);
	}

	public void guardarAdministrador(Administrador admin) throws AdministradorYaExistenteException {
		AdministradorDao.getInstancia().guardar(admin);
	}

	public List<Administrador> listarAdmin() {
		return AdministradorDao.getInstancia().listar();
	}

	public void actualizarAdministrador(Administrador admin) throws AdministradorNoExistenteException {
		AdministradorDao.getInstancia().actualizar(admin);
	}

	public void eliminarAdministrador(String id) throws AdministradorNoExistenteException {
		AdministradorDao.getInstancia().eliminar(id);
	}

	public Administrador buscarAdministrador(String id) throws AdministradorNoExistenteException {
		return AdministradorDao.getInstancia().buscar(id);
	}

	public boolean verificarAdministrador(String id) {
		return AdministradorDao.getInstancia().verificar(id);
	}

	public boolean hacerLogin(Loginable user) throws UsuarioNoExistenteException {
		return verificarLogin(user);
	}

	private boolean verificarLogin(Loginable user) throws UsuarioNoExistenteException {
		return verificarAdministrador(user.getUsuario()) || verificarCliente(user.getUsuario());
	}
}
