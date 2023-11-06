package co.edu.uniquindio.agenciaviajes.controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.model.Clima;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import co.edu.uniquindio.agenciaviajes.utils.UtilsFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class RegistroDestinoController implements Controllable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnAgregar;

	@FXML
	private Button btnSeleccionar;

	@FXML
	private ComboBox<Clima> cbClima;

	@FXML
	private GridPane contentPane;

	@FXML
	private Label lblCiudad;

	@FXML
	private Label lblClima;

	@FXML
	private Label lblDescripcion;

	@FXML
	private Label lblNombre;

	@FXML
	private Label lblSeleccionarImagen;

	@FXML
	private Label lblTitulo;

	@FXML
	private TextField txtCiudad;

	@FXML
	private TextField txtDescripcion;

	@FXML
	private TextField txtNombre;

	private int rowIndex = 0;
	private int colIndex = 0;

	private List<Image> imagenes = new ArrayList<>();

	@FXML
	void seleccionarImagenEvent(ActionEvent event) {
		seleccionarImagenAction();
	}

	@FXML
	void agregarDestinoEvent(ActionEvent event) {
		agregarDestinoAction();
	}

	private void agregarDestinoAction() {

	}

	private void seleccionarImagenAction() {
		FileChooser fChooser = new FileChooser();
		fChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagenes", "*.png", "*.jpg", "*.gif"),
				new FileChooser.ExtensionFilter("Todos los archivos", "*.*"));
		File file = fChooser.showOpenDialog(null);
		if (file != null) {
			String ruta = file.toURI().toString();
			var imagencita = new Image(ruta);
			var imgView = new ImageView(imagencita);
			imgView.setFitWidth(150);
			imgView.setFitHeight(150);
			imagenes.add(imagencita);
			addNewImage(imgView);
		} else {
			new Alert(AlertType.INFORMATION, "No se ha seleccionado ninguna imagen").show();
		}
	}

	@Override
	public void preInicializar() {
		UtilsFX.setAsNameTextField(txtNombre);
		UtilsFX.setAsNameTextField(txtCiudad);
	}

	private void addNewImage(ImageView imagen) {
		contentPane.add(imagen, colIndex, rowIndex);
		colIndex = 1 - colIndex;
		if (colIndex == 0)
			rowIndex++;
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub

	}

}
