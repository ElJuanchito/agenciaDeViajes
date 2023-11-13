package co.edu.uniquindio.agenciaviajes.controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import co.edu.uniquindio.agenciaviajes.exceptions.PeticionException;

public class PeticionController<Obj, Resultado> {
	private TipoPeticion tipo;
	private Obj objeto;
	private Resultado resultado;

	public PeticionController(TipoPeticion tipo, Obj objeto) {
		this.tipo = tipo;
		this.objeto = objeto;

	}

	public Resultado realizarPeticion() throws PeticionException {
		try (Socket socket = new Socket("localhost", 10)) {

			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

			oos.writeObject(Peticion.builder().tipo(tipo).peticion(objeto).build());

			Object resultPeticion = ois.readObject();
			oos.close();
			ois.close();

			if (resultPeticion instanceof PeticionException)
				throw ((PeticionException) resultPeticion);

		} catch (IOException e) {
			throw new PeticionException("No se pudo realizar la peticion (IOException): " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new PeticionException("No se pudo realizar la peticion (ClassNotFoundException): " + e.getMessage());
		}
		return (Resultado) resultado;
	}

}
