package co.edu.uniquindio.agenciaviajes.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class estadisticasController {

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
	private LineChart<?, ?> graphicPaquetes;

	@FXML
	private Label lblPaquetesReservados;

	@FXML
	void initialize() {
		// Configurar los ejes X e Y
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Categorías");
		yAxis.setLabel("Valores");

		// Crear el BarChart
		BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
		barChart.setTitle("Gráfico de Barras");

		// Crear una serie de datos
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName("Datos de ejemplo");
		series.setData(FXCollections.observableArrayList(new XYChart.Data<String, Number>("Categoría 1", 10),
				new XYChart.Data<String, Number>("Categoría 2", 10)));
//		// Agregar datos a la serie
//		series.getData().add(new XYChart.Data<>("Categoría 1", 10));
//		series.getData().add(new XYChart.Data<>("Categoría 2", 20));
//		series.getData().add(new XYChart.Data<>("Categoría 3", 15));
//		series.getData().add(new XYChart.Data<>("Categoría 4", 30));

		// Agregar la serie al BarChart
		barChart.getData().add(series);

		// Agregar el BarChart al controlador
		graphicDestinosReservados = barChart;

	}
}
