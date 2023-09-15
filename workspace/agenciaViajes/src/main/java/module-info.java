module co.edu.uniquindio.agenciaViajes {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.persistence;
	requires lombok;
	requires java.sql;

	opens co.edu.uniquindio.agenciaViajes.application to javafx.fxml;
	exports co.edu.uniquindio.agenciaViajes.application;

	opens co.edu.uniquindio.agenciaViajes.model to org.hibernate.orm.core;
	exports co.edu.uniquindio.agenciaViajes.model;

}
