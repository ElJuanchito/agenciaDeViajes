package co.edu.uniquindio.agenciaViajes.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaViajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaViajes.services.AnimationService;
import co.edu.uniquindio.agenciaViajes.services.Controllable;
import co.edu.uniquindio.agenciaViajes.ui.TipoVista;
import co.edu.uniquindio.agenciaViajes.ui.VistaManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginController implements Controllable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnIniciar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Label lblInfo;

    @FXML
    private Label lblNoRegistrado;

    @FXML
    private Label lblTitle;

    @FXML
    private ImageView pikachuImg;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void backEvent(ActionEvent event) {

    }

    @FXML
	void iniciarEvent(ActionEvent event) {
    	cambiarVentana(btnIniciar, TipoVista.DESTINOS);
	}

    @FXML
    void registrarEvent(ActionEvent event) {
    	cambiarVentana(btnRegistrar, TipoVista.REGISTRO);
    }

    @FXML
    void initialize() {
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

}
