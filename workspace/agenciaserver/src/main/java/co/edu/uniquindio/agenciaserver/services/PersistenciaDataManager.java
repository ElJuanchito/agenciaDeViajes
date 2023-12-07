package co.edu.uniquindio.agenciaserver.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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
	
	private static final String RUTA_ADMINISTRADOR="src/main/resources/co/edu/uniquindio/agenciaserver/data/administrador.dat";
	private static final String RUTA_CLIENTES="src/main/resources/co/edu/uniquindio/agenciaserver/data/clientes.dat";
	private static final String RUTA_GUIAS="src/main/resources/co/edu/uniquindio/agenciaserver/data/guias.dat";
	private static final String RUTA_PAQUETES="src/main/resources/co/edu/uniquindio/agenciaserver/data/paquetes.dat";
	private static final String RUTA_RESERVAS="src/main/resources/co/edu/uniquindio/agenciaserver/data/reservas.dat";

	@Override
	public void guardarCliente(Cliente cliente) throws ClienteYaExistenteException {
		// TODO 
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
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = deserealizar(id, ArrayList.class);
		for(Cliente cliente : clientes) {
			if(cliente.getIdentificacion().equals(id)) {
				return true;
			}
		} return false;
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
	
//------------------------------------------------------------------------------------------------------------------------------------------------
	
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

	
	
	
	private void throwReservaYaExistente(Long id) throws ReservaYaExistenteException {
		if (verificarReserva(id)) {
			throw new ReservaYaExistenteException(
					"La reserva " + id + ", ya existe en la lista.");
		}
	}
	
	private void throwReservaNoExistente(Long id) throws ReservaNoExistenteException {
		if (!verificarReserva(id)) {
			throw new ReservaNoExistenteException(
					"La reserva: " + id + ", no existe en la lista.");
		}
	}
	
	@Override
	public void guardarReserva(Reserva reserva) throws ReservaYaExistenteException {
		throwReservaYaExistente(reserva.getId());
		listarReserva().add(reserva);
	}

	@Override
	public List<Reserva> listarReserva() {
		try {
			ArchivoUtils.deserializarObjeto(RUTA_RESERVAS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void actualizarReserva(Reserva reserva) throws ReservaNoExistenteException {
		throwReservaNoExistente(reserva.getId());
		List<Reserva> listarReserva = listarReserva();
		for (int i = 0; i < listarReserva.size(); i++) {
			Reserva reservas = listarReserva.get(i);
			if(reservas.getId().equals(reserva.getId())) {
				listarReserva.set(i, reserva);
			}
		}
		
	}

	@Override
	public void eliminarReserva(Long id) throws ReservaNoExistenteException {
		throwReservaNoExistente(id);
		for (Reserva reservas : listarReserva()) {
			if(reservas.getId().equals(id)) {
				listarReserva().remove(reservas);
				return;
			}
		}
	}

	@Override
	public Reserva buscarReserva(Long id) throws ReservaNoExistenteException {
		throwReservaNoExistente(id);
		for (Reserva reservas : listarReserva()) {
			if(reservas.getId().equals(id))
				return reservas;
			}
		return null;
	}

	@Override
	public boolean verificarReserva(Long id) {
		for (Reserva reserva :listarReserva()) {
			if(reserva.getId().equals(id))
				return true;
		}
		return false;
	}
	
	
	private void throwAdministradorYaExistente(String id) throws AdministradorYaExistenteException {
		if (verificarAdministrador(id)) {
			throw new AdministradorYaExistenteException(
					"El administrador " + id + ", ya existe en la lista.");
		}
	}
	
	private void throwAdministradorNoExistente(String id) throws AdministradorNoExistenteException {
		if (!verificarAdministrador(id)) {
			throw new AdministradorNoExistenteException(
					"El adminstrador: " + id + ", no existe en la lista.");
		}
	}

	@Override
	public void guardarAdministrador(Administrador admin) throws AdministradorYaExistenteException {
		throwAdministradorYaExistente(admin.getUsuario());
		listarAdmin().add(admin);
	}

	@Override
	public List<Administrador> listarAdmin() {
		try {
			ArchivoUtils.deserializarObjeto(RUTA_ADMINISTRADOR);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void actualizarAdministrador(Administrador admin) throws AdministradorNoExistenteException {
			throwAdministradorNoExistente(admin.getUsuario());
			for(Administrador administradores: listarAdmin()) {
				if(administradores.getUsuario().equals(admin.getUsuario())) {
					listarAdmin().set(listarAdmin().indexOf(administradores), admin);
				}
			}
	}

	@Override
	public void eliminarAdministrador(String id) throws AdministradorNoExistenteException {
		throwAdministradorNoExistente(id);
		for (Administrador administradores : listarAdmin()) {
			if(administradores.getUsuario().equals(id)) {
				listarAdmin().remove(administradores);
				return;
			}
		}
	}

	@Override
	public Administrador buscarAdministrador(String id) throws AdministradorNoExistenteException {
		throwAdministradorNoExistente(id);
		for (Administrador administradores : listarAdmin()) {
			if(administradores.getUsuario().equals(id))
				return administradores;
			}
		return null;
	}

	@Override
	public boolean verificarAdministrador(String id) {
		for (Administrador administrador :listarAdmin()) {
			if(administrador.getUsuario().equals(id))
				return true;
		}
		return false;
	}

	@Override
	public boolean hacerLogin(Loginable user) throws UsuarioNoExistenteException {
		// TODO Auto-generated method stub
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T deserealizar(String ruta, Class<T> clase) {
		T resultado = null;
		try(FileInputStream fileIn = new FileInputStream(ruta);
				ObjectInputStream obIn = new ObjectInputStream(fileIn)) {
		resultado = (T)obIn.readObject();
		}catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public void serealizar(String ruta, Object objeto) {
		try(FileOutputStream fileOut = new FileOutputStream(ruta);
		ObjectOutputStream obOut = new ObjectOutputStream(fileOut)) {
			obOut.writeObject(objeto);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
