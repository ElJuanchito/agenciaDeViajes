package co.edu.uniquindio.agenciaviajes.controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.exceptions.ImagenNoObtenidaException;
import co.edu.uniquindio.agenciaviajes.model.GuiaTuristico;
import co.edu.uniquindio.agenciaviajes.model.Idioma;
import co.edu.uniquindio.agenciaviajes.model.Imagen;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import co.edu.uniquindio.agenciaviajes.utils.UtilsFX;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;

public class RegistroGuiaController implements Controllable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnImagen;

	@FXML
	private Button btnRegistrar;

	@FXML
	private TableColumn<Idioma, String> colIdiomas;

	@FXML
	private TableColumn<Idioma, String> colSelect;

	@FXML
	private ImageView imagePreview;

	@FXML
	private Label lblHoras;

	@FXML
	private Label lblIdentificacion;

	@FXML
	private Label lblImagen;

	@FXML
	private Label lblImagenSeleccionada;

	@FXML
	private Label lblNombre;

	@FXML
	private Label lblTitle;

	@FXML
	private TableView<Idioma> tblIdiomas;

	@FXML
	private TableView<Idioma> tblSelect;

	@FXML
	private TextField txtHoras;

	@FXML
	private TextField txtIdentificacion;

	@FXML
	private TextField txtNombre;

	@FXML
	private HBox imagenPane;

	private ObservableList<Idioma> listIdioma;
	private ObservableList<Idioma> listSelect;

	private Image imagenGuia;

	@FXML
	void registrarEvent(ActionEvent event) {
		registrarAction();
	}

	@FXML
	void selectionarImagenEvent(ActionEvent event) {
		selectionarImagenAction();
	}

	@FXML
	void seleccionarIdiomaEvent(MouseEvent event) {
		seleccionarIdiomaAction();
	}
	
	private void registrarAction() {
		Imagen imagencita = null;
		try {
			imagencita = Imagen.createImage(imagenGuia);
		} catch (ImagenNoObtenidaException e) {
			new Alert(AlertType.ERROR, e.getMessage() + ". Seleccione otra imagen").show();
		}
		
		GuiaTuristico guia = GuiaTuristico.builder()
				.nombreCompleto(txtNombre.getText().trim())
				.identificacion(txtIdentificacion.getText().trim())
				.expHoras(Integer.valueOf(txtHoras.getText().trim()))
				.imagen(imagencita)
				.idiomas(listSelect.stream().toArray(Idioma[]::new))
				.build();
		System.out.println(guia);
	}

	private void selectionarImagenAction() {
		FileChooser fChooser = new FileChooser();
		fChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagenes", "*.png", "*.jpg", "*.gif"),
				new FileChooser.ExtensionFilter("Todos los archivos", "*.*"));
		File file = fChooser.showOpenDialog(null);
		if (file != null) {
			String ruta = file.toURI().toString();
			imagenGuia = new Image(ruta);
			imagePreview.setImage(imagenGuia);
			imagenPane.setVisible(true);
			imagenPane.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
			imagenPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
			imagenPane.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

		} else {
			new Alert(AlertType.INFORMATION, "No se ha seleccionado ninguna imagen").show();
		}
	}

	private void seleccionarIdiomaAction() {
		Idioma aux = tblIdiomas.getSelectionModel().getSelectedItem();
		if (aux != null) {
			listIdioma.removeAll(aux);
			listSelect.addAll(aux);

			tblIdiomas.refresh();
			tblSelect.refresh();
		}
	}

	@Override
	public void preInicializar() {

		imagenPane.setMaxSize(0, 0);
		imagenPane.setPrefSize(0, 0);
		imagenPane.setMinSize(0, 0);

		UtilsFX.setAsIntegerTextfield(txtIdentificacion);
		UtilsFX.setAsNameTextField(txtNombre);
		UtilsFX.setAsIntegerTextfield(txtHoras);

		listIdioma = FXCollections.observableArrayList(Idioma.values());
		listSelect = FXCollections.observableArrayList();

		tblIdiomas.setItems(listIdioma);
		tblSelect.setItems(listSelect);
		colIdiomas.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getIdioma()));

		colSelect.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getIdioma()));

		tblIdiomas.refresh();
		tblSelect.refresh();

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
