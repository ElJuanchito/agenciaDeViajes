package co.edu.uniquindio.agenciaviajes.controllers;

import java.text.DecimalFormat;
import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.model.GuiaTuristico;
import co.edu.uniquindio.agenciaviajes.model.Idioma;
import co.edu.uniquindio.agenciaviajes.services.Controllable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CrearReservaController implements Controllable {

    @FXML
    private Button btnReservar;

    @FXML
    private TableColumn<GuiaTuristico, String> clmExp;

    @FXML
    private TableColumn<GuiaTuristico, String> clmId;

    @FXML
    private TableColumn<GuiaTuristico, String> clmIdioma;

    @FXML
    private TableColumn<GuiaTuristico, String> clmName;

    @FXML
    private DatePicker dtpFecha;

    @FXML
    private Label lblCant;

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblGuia;

    @FXML
    private Label lblNamePackage;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblinfoPackage;

    @FXML
    private Spinner<Integer> spnCant;

    @FXML
    private TableView<GuiaTuristico> tblGuias;

    @FXML
    private TableView<Idioma> tblIdiomas;

    @FXML
    void reservarEvent(ActionEvent event) {
    	
    }
  
	@Override
	public void preInicializar() {
		
		clmId.setCellValueFactory(cell->new ReadOnlyStringWrapper(cell.getValue().getIdentificacion()) );
		clmName.setCellValueFactory(cell-> new ReadOnlyStringWrapper(cell.getValue().getNombreCompleto()));
		
		DecimalFormat format= new DecimalFormat("#");
		
		clmExp.setCellValueFactory(cell->new ReadOnlyStringWrapper(format.format(cell.getValue().getExpHoras())));
		
		
		
		
		//Actualizacion de tablas por cada vez que se seleccione un guia
		tblGuias.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	        if (newValue != null) {
	            tblIdiomas.setItems(FXCollections.observableArrayList(newValue.getIdiomas()));
	        }
	    });
		
		
		
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0) {
            @Override
            public void decrement(int steps) {
                int currentValue = this.getValue();
                int newValue = currentValue - steps;
                if (newValue >= 0) {
                    this.setValue(newValue);
                } else {
                    this.setValue(0);
                }
            }

            @Override
            public void increment(int steps) {
                int currentValue = this.getValue();
                this.setValue(currentValue + steps);
            }
        };

        // Configurar el Spinner con el SpinnerValueFactory personalizado
        spnCant.setValueFactory(valueFactory);
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