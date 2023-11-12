package co.edu.uniquindio.agenciaviajes.controllers;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.exceptions.PeticionException;
import co.edu.uniquindio.agenciaviajes.model.Cliente;
import co.edu.uniquindio.agenciaviajes.model.GuiaTuristico;
import co.edu.uniquindio.agenciaviajes.model.Paquete;
import co.edu.uniquindio.agenciaviajes.model.Reserva;
import co.edu.uniquindio.agenciaviajes.viewcontrollers.MainPaneController;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CrearReservaLogicController {
	private static CrearReservaLogicController instance;
	private DateTimeFormatter pattern;

	public static CrearReservaLogicController getInstance() {
		if (instance == null)
			instance = new CrearReservaLogicController();
		return instance;
	}

	public void crearReserva(Integer cantPersonas, Paquete paquete, GuiaTuristico guiaTuristico) {

		Reserva reserva = Reserva.builder().cliente((Cliente) DataController.getInstance().getLoginActualValue())
				.cantPersonas(cantPersonas).paquete(paquete).guiaTuristico(guiaTuristico).build();
		try {
			Reserva reservaNueva = new PeticionController<Reserva, Reserva>(TipoPeticion.GUARDAR_RESERVA, reserva)
					.realizarPeticion();
			MainPaneController.getInstance()
					.showAlertAccept("Se ha creado una reserva, ¿quieres recibir un comprobante?", () -> {
						Platform.runLater(() -> crearPdfReserva(reservaNueva));
					});
		} catch (PeticionException e) {
			MainPaneController.getInstance().showAlert(e.getMessage());
		}
	}

	private void crearPdfReserva(final Reserva reservaNueva) {
		try {
			VistaManager.getInstance().crearPDFReserva(reservaNueva);
		} catch (FXMLException e) {
			throw new RuntimeException(e);
		}
	}

	public IntegerSpinnerValueFactory crearSpinnerValueFactory(int cupoMax) {
		return new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0) {
			@Override
			public void decrement(int steps) {
				int currentValue = this.getValue();
				int newValue = currentValue - steps;
				this.setValue(newValue >= 0 ? newValue : 0);
			}

			@Override
			public void increment(int steps) {
				if (this.getValue() + steps <= cupoMax)
					this.setValue(this.getValue() + steps);
			}
		};
	}

	private ReadOnlyStringWrapper getWraper(String s) {
		return new ReadOnlyStringWrapper(s);
	}

	public Callback<CellDataFeatures<GuiaTuristico, String>, ObservableValue<String>> crearColID() {
		return cell -> getWraper(cell.getValue().getIdentificacion());
	}

	public Callback<CellDataFeatures<GuiaTuristico, String>, ObservableValue<String>> crearColNombre() {
		return cell -> getWraper(cell.getValue().getNombreCompleto());
	}

	public Callback<CellDataFeatures<GuiaTuristico, String>, ObservableValue<String>> crearColExperiencia() {
		return cell -> getWraper(new DecimalFormat("#").format(cell.getValue().getExpHoras()));
	}

	public String getFechaFormato(LocalDateTime fecha) {
		if (pattern == null)
			pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy h:mm a");
		return fecha.format(pattern);
	}
}
