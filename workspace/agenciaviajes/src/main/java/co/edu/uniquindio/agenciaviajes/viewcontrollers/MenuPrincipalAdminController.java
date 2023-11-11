package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.SVGPath;

public class MenuPrincipalAdminController {
	
    @FXML
    private BorderPane centerPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblBtnDestinos;

    @FXML
    private Label lblbtnPaquetes;

    @FXML
    private Label lblBtnGuias;

    @FXML
    private Label lblBtnDestinos1;

    @FXML
    private Label lblBtnDestinos2;

    @FXML
    private SVGPath btnPerfil;

    @FXML
    private ScrollPane scrollCenter;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnExtra;

    @FXML
    void ClientesEvent(ActionEvent event) {

    }

    @FXML
    void backEvent(ActionEvent event){

    }

    @FXML
    void destinosEvent(ActionEvent event) {
    	cambiarVentana("gestionarDestinos");

    }

    @FXML
    void extraEvent(ActionEvent event) {

    }

    @FXML
    void guiasEvent(ActionEvent event) {
    	cambiarVentana("gestionarGuias");

    }

    @FXML
    void masEvent(ActionEvent event) {

    }

    @FXML
    void nextEvent(ActionEvent event) {

    }

    @FXML
    void paquetesEvent(ActionEvent event) {
    	cambiarVentana("gestionarPaquetes");

    }

    @FXML
    void initialize() {

    }
    
    private void cambiarVentana(String fxmlname) {
		try {
			Node nodo = App.loadFXML(fxmlname);
			setCenter(nodo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setCenter(Node node) {
		centerPane.setCenter(node);
	}

}
