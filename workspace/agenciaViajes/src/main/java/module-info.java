module co.edu.uniquindio.agenciaViajes {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.persistence;
	requires lombok;

	opens co.edu.uniquindio.agenciaViajes.application to javafx.fxml;
	exports co.edu.uniquindio.agenciaViajes.application;
}
