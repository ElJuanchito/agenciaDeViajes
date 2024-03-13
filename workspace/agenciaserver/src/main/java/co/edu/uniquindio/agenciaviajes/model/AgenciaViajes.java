package co.edu.uniquindio.agenciaviajes.model;

import java.util.List;

import co.edu.uniquindio.agenciaserver.services.DataManager;
import co.edu.uniquindio.agenciaviajes.exceptions.AdministradorNoExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.AdministradorYaExistenteException;
import co.edu.uniquindio.agenciaviajes.exceptions.ClienteNoExistenteException;
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
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@NonNull
@Getter
@Setter
public class AgenciaViajes implements DataManager {

	private List<Cliente> listaClientes;
	private List<Administrador> listaAdmins;
	private List<Destino> listaDestinos;
	private List<Paquete> listaPaquetes;
	private List<Imagen> listaImagenes;

	public static void main(String[] args) {

	}
}
