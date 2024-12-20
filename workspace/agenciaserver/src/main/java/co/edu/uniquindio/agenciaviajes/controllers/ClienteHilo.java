package co.edu.uniquindio.agenciaviajes.controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.stream.Collectors;

import co.edu.uniquindio.agenciaserver.services.DataManager;
import co.edu.uniquindio.agenciaserver.services.HibernateDataManager;
import co.edu.uniquindio.agenciaserver.utils.EmailSender;
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
import co.edu.uniquindio.agenciaviajes.model.BusquedaPaquetes;
import co.edu.uniquindio.agenciaviajes.model.Cliente;
import co.edu.uniquindio.agenciaviajes.model.CorreoFile;
import co.edu.uniquindio.agenciaviajes.model.Destino;
import co.edu.uniquindio.agenciaviajes.model.GuiaTuristico;
import co.edu.uniquindio.agenciaviajes.model.Imagen;
import co.edu.uniquindio.agenciaviajes.model.Loginable;
import co.edu.uniquindio.agenciaviajes.model.Paquete;
import co.edu.uniquindio.agenciaviajes.model.Reserva;
import co.edu.uniquindio.agenciaviajes.services.DataManagerImpl;

public class ClienteHilo implements Runnable {

	private final Socket sCliente;

	public ClienteHilo(Socket sCliente) {
		this.sCliente = sCliente;
	}

	@Override
	public void run() {
		try (sCliente) {

			ObjectInputStream obIn = new ObjectInputStream(sCliente.getInputStream());
			ObjectOutputStream obOut = new ObjectOutputStream(sCliente.getOutputStream());
			Peticion peticion = (Peticion) obIn.readObject();

			DataManagerImpl dataManager = DataManagerImpl.getInstance();

			System.out.println(dataManager.listarCliente());

			switch (peticion.getTipo()) {
			case HACER_LOGIN -> {
				System.out.println("inici");
				Loginable user = (Loginable) peticion.getPeticion();
				Loginable resultado = null;
				try {
					if (dataManager.hacerLogin(user)) {
						resultado = dataManager.verificarAdministrador(user.getUsuario())
								? (Administrador) dataManager.buscarAdministrador(user.getUsuario())
								: (Cliente) dataManager.buscarCliente(user.getUsuario());
						System.out.println(resultado.toString());
					}

					obOut.writeObject(null);
				} catch (UsuarioNoExistenteException | AdministradorNoExistenteException e) {
					System.out.println(e.getMessage());
					obOut.writeObject(null);
				}
				break;
			}
			case GUARDAR_CLIENTE -> {
				try {
					Cliente cliente = (Cliente) peticion.getPeticion();
					dataManager.guardarCliente(cliente);
					obOut.writeObject(true);
				} catch (ClienteYaExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case BUSCAR_CLIENTE -> {
				try {
					String id = (String) peticion.getPeticion();
					Cliente cliente = dataManager.buscarCliente(id);
					obOut.writeObject(cliente);
				} catch (ClienteNoExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case ACTUALIZAR_CLIENTE -> {
				try {
					Cliente cliente = (Cliente) peticion.getPeticion();
					dataManager.actualizarCliente(cliente);
					cliente = dataManager.buscarCliente(cliente.getIdentificacion());
					obOut.writeObject(true);
				} catch (ClienteNoExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case ELIMINAR_CLIENTE -> {
				try {
					String id = (String) peticion.getPeticion();
					dataManager.eliminarCliente(id);
					obOut.writeObject(true);
				} catch (ClienteNoExistenteException e) {
					obOut.writeObject(e);
				}
			}
			case VERIFICAR_CLIENTE -> {
				String id = (String) peticion.getPeticion();
				boolean respuesta = dataManager.verificarCliente(id);
				obOut.writeObject(respuesta);
			}

			case LISTAR_CLIENTE -> {
				obOut.writeObject(dataManager.listarCliente());
			}

			case GUARDAR_DESTINO -> {
				try {
					Destino destino = (Destino) peticion.getPeticion();
					dataManager.agregarDestino(destino);
					obOut.writeObject(destino);
				} catch (DestinoYaExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case BUSCAR_DESTINO -> {
				try {
					Long id = (Long) peticion.getPeticion();
					Destino destino = dataManager.buscarDestino(id);
					obOut.writeObject(destino);
				} catch (DestinoNoExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case ACTUALIZAR_DESTINO -> {
				try {
					Destino destino = (Destino) peticion.getPeticion();
					dataManager.actualizarDestino(destino);
					destino = dataManager.buscarDestino(destino.getId());
					obOut.writeObject(true);
				} catch (DestinoNoExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case ELIMINAR_DESTINO -> {
				try {
					Long id = (Long) peticion.getPeticion();
					dataManager.eliminarDestino(id);
					obOut.writeObject(true);
				} catch (DestinoNoExistenteException e) {
					obOut.writeObject(e);
				}
			}
			case VERIFICAR_DESTINO -> {
				Long id = (Long) peticion.getPeticion();
				boolean respuesta = dataManager.verificarDestino(id);
				obOut.writeObject(respuesta);
			}
			case LISTAR_DESTINO -> {
				obOut.writeObject(dataManager.listarDestino());
			}

			case GUARDAR_GUIA -> {
				try {
					GuiaTuristico guia = (GuiaTuristico) peticion.getPeticion();
					dataManager.guardarGuia(guia);
					obOut.writeObject(true);
				} catch (GuiaYaExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case BUSCAR_GUIA -> {
				try {
					String id = (String) peticion.getPeticion();
					GuiaTuristico guia = dataManager.buscarGuia(id);
					obOut.writeObject(guia);
				} catch (GuiaNoExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case ACTUALIZAR_GUIA -> {
				try {
					GuiaTuristico guia = (GuiaTuristico) peticion.getPeticion();
					dataManager.actualizarGuia(guia);
					guia = dataManager.buscarGuia(guia.getIdentificacion());
					obOut.writeObject(true);
				} catch (GuiaNoExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case ELIMINAR_GUIA -> {
				try {
					String id = (String) peticion.getPeticion();
					dataManager.eliminarGuia(id);
					obOut.writeObject(true);
				} catch (GuiaNoExistenteException e) {
					obOut.writeObject(e);
				}
			}
			case VERIFICAR_GUIA -> {
				String id = (String) peticion.getPeticion();
				boolean respuesta = dataManager.verificarGuia(id);
				obOut.writeObject(respuesta);
			}
			case LISTAR_GUIA -> {
				obOut.writeObject(dataManager.listarGuia());
			}

			case GUARDAR_IMAGEN -> {
				try {
					Imagen imagen = (Imagen) peticion.getPeticion();
					dataManager.guardarImagen(imagen);
					obOut.writeObject(imagen);
				} catch (ImagenYaExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}

			case BUSCAR_IMAGEN -> {
				try {
					Long id = (Long) peticion.getPeticion();
					Imagen imagen = dataManager.buscarImagen(id);
					obOut.writeObject(imagen);
				} catch (ImagenNoExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}

			case ACTUALIZAR_IMAGEN -> {
				try {
					Imagen imagen = (Imagen) peticion.getPeticion();
					dataManager.actualizarImagen(imagen);
					imagen = dataManager.buscarImagen(imagen.getId());
					obOut.writeObject(true);
				} catch (ImagenNoExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case ELIMINAR_IMAGEN -> {
				try {
					Long id = (Long) peticion.getPeticion();
					dataManager.eliminarImagen(id);
					obOut.writeObject(true);
				} catch (ImagenNoExistenteException e) {
					obOut.writeObject(e);
				}
			}
			case VERIFICAR_IMAGEN -> {
				Long id = (Long) peticion.getPeticion();
				boolean respuesta = dataManager.verificarImagen(id);
				obOut.writeObject(respuesta);
			}
			case LISTAR_IMAGEN -> {
				obOut.writeObject(dataManager.listarImagen());
			}

			case GUARDAR_PAQUETE -> {
				try {
					Paquete paquete = (Paquete) peticion.getPeticion();
					dataManager.guardarPaquete(paquete);
					obOut.writeObject(paquete);
				} catch (PaqueteYaExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case BUSCAR_PAQUETE -> {
				try {
					Long id = (Long) peticion.getPeticion();
					Paquete paquete = dataManager.buscarPaquete(id);
					obOut.writeObject(paquete);
				} catch (PaqueteNoExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case ACTUALIZAR_PAQUETE -> {
				try {
					Paquete paquete = (Paquete) peticion.getPeticion();
					dataManager.actualizarPaquete(paquete);
					paquete = dataManager.buscarPaquete(paquete.getId());
					obOut.writeObject(true);
				} catch (PaqueteNoExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case ELIMINAR_PAQUETE -> {
				try {
					Long id = (Long) peticion.getPeticion();
					dataManager.eliminarPaquete(id);
					obOut.writeObject(true);
				} catch (PaqueteNoExistenteException e) {
					obOut.writeObject(e);
				}
			}
			case VERIFICAR_PAQUETE -> {
				Long id = (Long) peticion.getPeticion();
				boolean respuesta = dataManager.verificarPaquete(id);
				obOut.writeObject(respuesta);
			}
			case LISTAR_PAQUETE -> {
				obOut.writeObject(dataManager.listarPaquete());
			}

			case GUARDAR_RESERVA -> {
				try {
					Reserva reserva = (Reserva) peticion.getPeticion();
					dataManager.guardarReserva(reserva);
					obOut.writeObject(reserva);
				} catch (ReservaYaExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case BUSCAR_RESERVA -> {
				try {
					Long id = (Long) peticion.getPeticion();
					Reserva reserva = dataManager.buscarReserva(id);
					obOut.writeObject(reserva);
				} catch (ReservaNoExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case ACTUALIZAR_RESERVA -> {
				try {
					Reserva reserva = (Reserva) peticion.getPeticion();
					dataManager.actualizarReserva(reserva);
					reserva = dataManager.buscarReserva(reserva.getId());
					obOut.writeObject(true);
				} catch (ReservaNoExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case ELIMINAR_RESERVA -> {
				try {
					Long id = (Long) peticion.getPeticion();
					dataManager.eliminarReserva(id);
					obOut.writeObject(true);
				} catch (ReservaNoExistenteException e) {
					obOut.writeObject(e);
				}
			}
			case VERIFICAR_RESERVA -> {
				Long id = (Long) peticion.getPeticion();
				boolean respuesta = dataManager.verificarReserva(id);
				obOut.writeObject(respuesta);
			}
			case LISTAR_RESERVA -> {
				obOut.writeObject(dataManager.listarReserva());
			}
			case GUARDAR_ADMIN -> {
				try {
					Administrador admin = (Administrador) peticion.getPeticion();
					dataManager.guardarAdministrador(admin);
					obOut.writeObject(true);
				} catch (AdministradorYaExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case BUSCAR_ADMIN -> {
				try {
					String id = (String) peticion.getPeticion();
					Administrador admin = dataManager.buscarAdministrador(id);
					obOut.writeObject(admin);
				} catch (AdministradorNoExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case ACTUALIZAR_ADMIN -> {
				try {
					Administrador admin = (Administrador) peticion.getPeticion();
					dataManager.actualizarAdministrador(admin);
					admin = dataManager.buscarAdministrador(admin.getIdentificacion());
					obOut.writeObject(true);
				} catch (AdministradorNoExistenteException e) {
					obOut.writeObject(e);
				}
				break;
			}
			case ELIMINAR_ADMIN -> {
				try {
					String id = (String) peticion.getPeticion();
					dataManager.eliminarAdministrador(id);
					obOut.writeObject(true);
				} catch (AdministradorNoExistenteException e) {
					obOut.writeObject(e);
				}
			}
			case VERIFICAR_ADMIN -> {
				String id = (String) peticion.getPeticion();
				boolean respuesta = dataManager.verificarAdministrador(id);
				obOut.writeObject(respuesta);
			}

			case LISTAR_ADMIN -> {
				obOut.writeObject(dataManager.listarAdmin());
			}
			case ENVIAR_PDF -> {
				obOut.writeObject(EmailSender.getInstance().enviarCorreoReserva((CorreoFile) peticion.getPeticion()));
			}
			case FILTRAR_PAQUETES -> obOut.writeObject(dataManager.listarPaquete().stream()
					.filter(((BusquedaPaquetes) peticion.getPeticion()).getPredicate())
					.collect(Collectors.toCollection(ArrayList::new)));
			}
			obIn.close();
			obOut.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
