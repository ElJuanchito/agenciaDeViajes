package co.edu.uniquindio.agenciaViajes.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaViajes.model.Destino;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.Setter;

public class DestinoController implements Initializable{

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblCity;

	@FXML
	private Label lblDescription;

	@FXML
	private Label lblName;

	@FXML
	private Label lblWeather;

	@FXML
	private Label txtCity;

	@FXML
	private Label txtDescription;

	@FXML
	private Label txtName;

	@FXML
	private Label txtWeather;

	@Setter
	private Destino destino;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtName.setText(destino.getNombre());
		txtCity.setText(destino.getCiudad());
		txtDescription.setText(destino.getDescripcion());
		txtWeather.setText(destino.getClima().getText());
	}

}
