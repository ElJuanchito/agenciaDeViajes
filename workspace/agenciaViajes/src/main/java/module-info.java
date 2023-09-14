module co.edu.uniquindio.agenciaViajes {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.persistence;
	requires lombok;
	requires javafx.graphics;

	opens co.edu.uniquindio.agenciaViajes.application to javafx.fxml;
	opens co.edu.uniquindio.agenciaViajes.controllers to javafx.fxml;

	exports co.edu.uniquindio.agenciaViajes.controllers to javafx.fxml;
	exports co.edu.uniquindio.agenciaViajes.application;
}
