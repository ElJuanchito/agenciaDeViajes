package co.edu.uniquindio.agenciaviajes.controllers;

import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.model.Idioma;
import co.edu.uniquindio.agenciaviajes.services.DataControllable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RegistroGuiaController implements DataControllable<String>{

    @FXML
    private Button btnImagen;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Label lblHoras;

    @FXML
    private Label lblIdentificacion;

    @FXML
    private Label lblIdiomas;

    @FXML
    private Label lblImagen;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblSelectImagen;

    @FXML
    private Label lblTitle;

    @FXML
    private ListView<Idioma> listaIdiomas;

    @FXML
    private TextField txtHoras;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextArea txtSeleccionados;

    @FXML
    void registrarEvent(ActionEvent event) {

    }

    @FXML
    void selectionarImagenEvent(ActionEvent event) {

    }

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inicializarDatos(String dato) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preInicializar() {
		// TODO Auto-generated method stub
		
	}

}
