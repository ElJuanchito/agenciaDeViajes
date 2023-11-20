package co.edu.uniquindio.agenciaserver.application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import co.edu.uniquindio.agenciaviajes.controllers.ClienteHilo;

public class ServerMain {

	public static void main(String[] args) {
		int port = 10;
		int hilosMaximos = 5;

		ExecutorService executor = Executors.newFixedThreadPool(hilosMaximos);

		try (ServerSocket server = new ServerSocket(port)) {
			System.out.println("Esperando peticiones...");
			while (true) {
				Socket socket = server.accept();
				System.out.println("cliente conectado");

				ClienteHilo hilo = new ClienteHilo(socket);
				executor.execute(hilo);

			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			executor.shutdown();
		}
	}
}
