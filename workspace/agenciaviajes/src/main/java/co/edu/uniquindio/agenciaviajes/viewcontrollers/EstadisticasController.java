package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

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
	private BarChart<String, Number> graphicDestinosReservados;

	@FXML
	private Label lblDestinosReservados;

	@FXML
	private PieChart graphicGuias;

	@FXML
	private Label lblGuiasPuntuados;

	@FXML
	private PieChart graphicDestinosBuscados;

	@FXML
	private Label lblDestinosBuscados;

	@FXML
	private LineChart<String, Number> graphicPaquetes;

	@FXML
	private Label lblPaquetesReservados;

	@FXML
	void initialize() {
	}

	public void inicializarGraphicDestinosBuscados() {
		// Crear datos para el gráfico (esto es solo un ejemplo, debes adaptarlo a tus
		// necesidades)
		PieChart.Data dato1 = new PieChart.Data("Etiqueta1", 30);
		PieChart.Data dato2 = new PieChart.Data("Etiqueta2", 40);
		PieChart.Data dato3 = new PieChart.Data("Etiqueta3", 20);

		// Limpiar datos existentes en el gráfico
		graphicDestinosBuscados.getData().clear();

		// Agregar nuevos datos al gráfico
		graphicDestinosBuscados.getData().addAll(dato1, dato2, dato3);
	}

	public void inicializarGraphicDestinosReservados() {
		// Crea una serie de datos
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.getData().add(new XYChart.Data<>("San Andres", 10));
		series.getData().add(new XYChart.Data<>("Cartagena", 20));
		series.getData().add(new XYChart.Data<>("Santa Marta", 15));

		// Agrega la serie al BarChart
		graphicDestinosReservados.getData().add(series);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void inicializarGraficaPaquetes() {
		// Crear una serie de datos
		XYChart.Series series = new XYChart.Series();
		series.getData().add(new XYChart.Data("Dato 1", 10));
		series.getData().add(new XYChart.Data("Dato 2", 20));
		series.getData().add(new XYChart.Data("Dato 3", 15));

		// Limpiar cualquier dato existente en la gráfica
		graphicPaquetes.getData().clear();

		// Agregar la serie a la gráfica
		graphicPaquetes.getData().add(series);
	}

	public void inicializarGraphicGuias() {
		// Crear datos para el gráfico (esto es solo un ejemplo, debes adaptarlo a tus
		// necesidades)
		PieChart.Data dato1 = new PieChart.Data("Etiqueta1", 30);
		PieChart.Data dato2 = new PieChart.Data("Etiqueta2", 40);
		PieChart.Data dato3 = new PieChart.Data("Etiqueta3", 20);

		// Limpiar datos existentes en el gráfico
		graphicGuias.getData().clear();

		// Agregar nuevos datos al gráfico
		graphicGuias.getData().addAll(dato1, dato2, dato3);
	}

	@Override
	public void preInicializar() {
		cargarGraficos();
	}

	private void cargarGraficos() {
		inicializarGraphicDestinosBuscados();
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
}
