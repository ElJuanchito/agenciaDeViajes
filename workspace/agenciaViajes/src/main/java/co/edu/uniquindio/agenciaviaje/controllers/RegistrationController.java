package co.edu.uniquindio.agenciaviaje.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviaje.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviaje.services.AnimationService;
import co.edu.uniquindio.agenciaviaje.services.Controllable;
import co.edu.uniquindio.agenciaviaje.ui.TipoVista;
import co.edu.uniquindio.agenciaviaje.ui.VistaManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class RegistrationController implements Controllable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegistro;

    @FXML
    private Label lblInfo;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblYaRegistrado;

    @FXML
    private ImageView pikachuImg;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtTelefono;

    @FXML
    void backEvent(ActionEvent event) {

    }

    @FXML
    void loginEvent(ActionEvent event) {
    	cambiarVentana(btnLogin, TipoVista.LOGIN);
    }

    @FXML
    void registroEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

	@Override
	public void preInicializar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub
		
	}
	
	private void cambiarVentana(Button btn, TipoVista tipo) {
		AnimationService.getInstance().ejecutarAccionBtn(btn, () -> {
			try {
				VistaManager.getInstance().cambiarVista(tipo, null);
			} catch (FXMLException e) {
				e.printStackTrace();
			}
		});
	}

}
