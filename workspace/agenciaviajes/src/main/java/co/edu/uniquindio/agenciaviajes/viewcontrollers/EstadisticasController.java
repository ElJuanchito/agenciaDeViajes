package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.controllers.PeticionController;
import co.edu.uniquindio.agenciaviajes.controllers.TipoPeticion;
import co.edu.uniquindio.agenciaviajes.exceptions.PeticionException;
import co.edu.uniquindio.agenciaviajes.model.Destino;
import co.edu.uniquindio.agenciaviajes.model.GuiaTuristico;
import co.edu.uniquindio.agenciaviajes.model.Paquete;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class EstadisticasController implements Controllable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblTitle;

	@FXML
	private BarChart<String, Number> graphicDestinosPuntuados;

	@FXML
	private Label lblDestinosReservados;

	@FXML
	private PieChart graphicGuias;

	@FXML
	private Label lblGuiasPuntuados;

	@FXML
	private PieChart graphicDestinosReservados;

	@FXML
	private Label lblDestinosBuscados;

	@FXML
	private LineChart<String, Number> graphicPaquetes;

	@FXML
	private Label lblPaquetesReservados;

	@FXML
	void initialize() {
	}

	public void inicializarGraphicDestinosReservados() {
		PeticionController <Void, List<Destino>> peticion = new PeticionController<Void, List<Destino>>(TipoPeticion.LISTAR_DESTINO, null);
		
		try {
			List<Destino> realizarPeticion = peticion.realizarPeticion();
			for (Destino guia : realizarPeticion) {
			    String etiqueta= guia.getNombre();
			    PieChart.Data dato = new PieChart.Data(etiqueta, guia.getReservas().size());
			    graphicDestinosReservados.getData().add(dato);
			    }
		} catch (PeticionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void inicializarGraphicDestinosPuntuados() {
		// Crea una serie de datos
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		PeticionController <Void, List<Destino>> peticion = new PeticionController<Void, List<Destino>>(TipoPeticion.LISTAR_DESTINO, null);
		
		try {
			List<Destino> realizarPeticion = peticion.realizarPeticion();
			for (Destino destino : realizarPeticion) {
			    String etiqueta= destino.getNombre();
			        series.getData().add(new XYChart.Data<>(etiqueta, destino.getPromedio()));
			    }
			graphicDestinosPuntuados.getData().add(series);
		} catch (PeticionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public void inicializarGraficaPaquetes() {
		// Crea una serie de datos
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		PeticionController <Void, List<Paquete>> peticion = new PeticionController<Void, List<Paquete>>(TipoPeticion.LISTAR_PAQUETE, null);
		
		try {
			List<Paquete> realizarPeticion = peticion.realizarPeticion();
			for (Paquete paquete : realizarPeticion) {
			    String etiqueta= paquete.getNombre();
			        series.getData().add(new XYChart.Data<>(etiqueta, paquete.getReservas().size()));
			    }
			graphicPaquetes.getData().add(series);
		} catch (PeticionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void inicializarGraphicGuias() {

		PeticionController <Void, List<GuiaTuristico>> peticion = new PeticionController<Void, List<GuiaTuristico>>(TipoPeticion.LISTAR_GUIA, null);
		
		try {
			List<GuiaTuristico> realizarPeticion = peticion.realizarPeticion();
			for (GuiaTuristico guia : realizarPeticion) {
			    String etiqueta= guia.getNombreCompleto();
			    PieChart.Data dato = new PieChart.Data(etiqueta, guia.getPromedio());
			    graphicGuias.getData().add(dato);
			    }
		} catch (PeticionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void preInicializar() {
		cargarGraficos();
	}

	private void cargarGraficos() {
		inicializarGraphicDestinosPuntuados();
		inicializarGraphicDestinosReservados();
		inicializarGraficaPaquetes();
		inicializarGraphicGuias();
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		lblTitle.setText(bundle.getString("EstadisticasController.lblTitle"));
		lblDestinosReservados.setText(bundle.getString("EstadisticasController.lblDestinosReservados"));
		lblDestinosBuscados.setText(bundle.getString("EstadisticasController.lblDestinosBuscados"));
		lblGuiasPuntuados.setText(bundle.getString("EstadisticasController.lblGuiasPuntuados"));
		lblPaquetesReservados.setText(bundle.getString("EstadisticasController.lblPaquetesReservados"));
	}

	@Override
	public void clearData() {
		cargarGraficos();
	}
	
	public void imprimirInformacionDestinos(List<Destino> destinos) {
        
    }
}
