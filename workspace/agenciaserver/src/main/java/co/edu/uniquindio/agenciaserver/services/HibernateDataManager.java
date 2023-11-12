package co.edu.uniquindio.agenciaserver.services;

import java.util.List;

import co.edu.uniquindio.agenciaserver.dao.AdministradorDao;
import co.edu.uniquindio.agenciaserver.dao.ClienteDao;
import co.edu.uniquindio.agenciaserver.dao.DestinoDao;
import co.edu.uniquindio.agenciaserver.dao.GuiaDao;
import co.edu.uniquindio.agenciaserver.dao.ImagenDao;
import co.edu.uniquindio.agenciaserver.dao.PaqueteDao;
import co.edu.uniquindio.agenciaserver.dao.ReservaDao;
import co.edu.uniquindio.agenciaserver.exceptions.AdministradorNoExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.AdministradorYaExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.ClienteYaExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.DestinoNoExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.DestinoYaExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.GuiaNoExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.GuiaYaExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.ImagenNoExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.ImagenYaExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.PaqueteNoExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.PaqueteYaExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.ReservaNoExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.ReservaYaExistenteException;
import co.edu.uniquindio.agenciaserver.exceptions.UsuarioNoExistenteException;
import co.edu.uniquindio.agenciaserver.model.Administrador;
import co.edu.uniquindio.agenciaserver.model.Cliente;
import co.edu.uniquindio.agenciaserver.model.Destino;
import co.edu.uniquindio.agenciaserver.model.GuiaTuristico;
import co.edu.uniquindio.agenciaserver.model.Imagen;
import co.edu.uniquindio.agenciaserver.model.Loginable;
import co.edu.uniquindio.agenciaserver.model.Paquete;
import co.edu.uniquindio.agenciaserver.model.Reserva;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class HibernateDataManager implements DataManager {

	private static HibernateDataManager instancia;

	public static HibernateDataManager getInstance() {
		if (instancia == null)
			instancia = new HibernateDataManager();
		return instancia;
	}

	@Override
	public void guardarCliente(Cliente cliente) throws ClienteYaExistenteException {
		ClienteDao.getInstancia().guardar(cliente);
	}

	@Override
	public List<Cliente> listarCliente() {
		return ClienteDao.getInstancia().listar();
	}

	@Override
	public void actualizarCliente(Cliente cliente) throws ClienteNoExistenteException {
		ClienteDao.getInstancia().actualizar(cliente);
	}

	@Override
	public void eliminarCliente(String id) throws ClienteNoExistenteException {
		ClienteDao.getInstancia().eliminar(id);
	}

	@Override
	public Cliente buscarCliente(String id) throws ClienteNoExistenteException {
		return ClienteDao.getInstancia().buscar(id);
	}

	@Override
	public boolean verificarCliente(String id) {
		return ClienteDao.getInstancia().verificar(id);
	}

	@Override
	public void guardarDestino(Destino destino) throws DestinoYaExistenteException {
		DestinoDao.getInstancia().guardar(destino);
	}

	@Override
	public List<Destino> listarDestino() {
		return DestinoDao.getInstancia().listar();
	}

	@Override
	public void actualizarDestino(Destino destino) throws DestinoNoExistenteException {
		DestinoDao.getInstancia().actualizar(destino);
	}

	@Override
	public void eliminarDestino(Long id) throws DestinoNoExistenteException {
		DestinoDao.getInstancia().eliminar(id);
	}

	@Override
	public Destino buscarDestino(Long id) throws DestinoNoExistenteException {
		return DestinoDao.getInstancia().buscar(id);
	}

	@Override
	public boolean verificarDestino(Long id) {
		return DestinoDao.getInstancia().verificar(id);
	}

	@Override
	public void guardarGuia(GuiaTuristico guia) throws GuiaYaExistenteException {
		GuiaDao.getInstancia().guardar(guia);
	}

	@Override
	public List<GuiaTuristico> listarGuia() {
		return GuiaDao.getInstancia().listar();
	}

	@Override
	public void actualizarGuia(GuiaTuristico guia) throws GuiaNoExistenteException {
		GuiaDao.getInstancia().actualizar(guia);
	}

	@Override
	public void eliminarGuia(String id) throws GuiaNoExistenteException {
		GuiaDao.getInstancia().eliminar(id);
	}

	@Override
	public GuiaTuristico buscarGuia(String id) throws GuiaNoExistenteException {
		return GuiaDao.getInstancia().buscar(id);
	}

	@Override
	public boolean verificarGuia(String id) {
		return GuiaDao.getInstancia().verificar(id);
	}

	@Override
	public void guardarImagen(Imagen imagen) throws ImagenYaExistenteException {
		ImagenDao.getInstancia().guardar(imagen);

	}

	@Override
	public List<Imagen> listarImagen() {
		return ImagenDao.getInstancia().listar();
	}

	@Override
	public void actualizarImagen(Imagen imagen) throws ImagenNoExistenteException {
		ImagenDao.getInstancia().actualizar(imagen);
	}

	@Override
	public void eliminarImagen(Long id) throws ImagenNoExistenteException {
		ImagenDao.getInstancia().eliminar(id);
	}

	@Override
	public Imagen buscarImagen(Long id) throws ImagenNoExistenteException {
		return ImagenDao.getInstancia().buscar(id);
	}

	@Override
	public boolean verificarImagen(Long id) {
		return ImagenDao.getInstancia().verificar(id);
	}

	@Override
	public void guardarPaquete(Paquete paquete) throws PaqueteYaExistenteException {
		PaqueteDao.getInstancia().guardar(paquete);
	}

	@Override
	public List<Paquete> listarPaquete() {
		return PaqueteDao.getInstancia().listar();
	}

	@Override
	public void actualizarPaquete(Paquete paquete) throws PaqueteNoExistenteException {
		PaqueteDao.getInstancia().actualizar(paquete);

	}

	@Override
	public void eliminarPaquete(Long id) throws PaqueteNoExistenteException {
		PaqueteDao.getInstancia().eliminar(id);
	}

	@Override
	public Paquete buscarPaquete(Long id) throws PaqueteNoExistenteException {
		return PaqueteDao.getInstancia().buscar(id);
	}

	@Override
	public void guardarReserva(Reserva reserva) throws ReservaYaExistenteException {
		ReservaDao.getInstancia().guardar(reserva);
	}

	@Override
	public boolean verificarPaquete(Long id) {
		return ReservaDao.getInstancia().verificar(id);
	}

	@Override
	public List<Reserva> listarReserva() {
		return ReservaDao.getInstancia().listar();
	}

	@Override
	public void actualizarReserva(Reserva reserva) throws ReservaNoExistenteException {
		ReservaDao.getInstancia().actualizar(reserva);
	}

	@Override
	public void eliminarReserva(Long id) throws ReservaNoExistenteException {
		ReservaDao.getInstancia().eliminar(id);
	}

	@Override
	public Reserva buscarReserva(Long id) throws ReservaNoExistenteException {
		return ReservaDao.getInstancia().buscar(id);
	}

	@Override
	public boolean verificarReserva(Long id) {
		return ReservaDao.getInstancia().verificar(id);
	}

	@Override
	public void guardarAdministrador(Administrador admin) throws AdministradorYaExistenteException {
		AdministradorDao.getInstancia().guardar(admin);
	}

	@Override
	public List<Administrador> listarAdmin() {
		return AdministradorDao.getInstancia().listar();
	}

	@Override
	public void actualizarAdministrador(Administrador admin) throws AdministradorNoExistenteException {
		AdministradorDao.getInstancia().actualizar(admin);
	}

	@Override
	public void eliminarAdministrador(String id) throws AdministradorNoExistenteException {
		AdministradorDao.getInstancia().eliminar(id);
	}

	@Override
	public Administrador buscarAdministrador(String id) throws AdministradorNoExistenteException {
		return AdministradorDao.getInstancia().buscar(id);
	}

	@Override
	public boolean verificarAdministrador(String id) {
		return AdministradorDao.getInstancia().verificar(id);
	}

	@Override
	public boolean hacerLogin(Loginable user) throws UsuarioNoExistenteException {
		return verificarLogin(user);
	}

	private boolean verificarLogin(Loginable user) throws UsuarioNoExistenteException {
		return verificarAdministrador(user.getUsuario()) || verificarCliente(user.getUsuario());
	}
}
