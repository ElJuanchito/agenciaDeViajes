module co.edu.uniquindio.agenciaViajes {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.persistence;
	requires lombok;
	requires javafx.graphics;
	requires java.sql;
	requires org.hibernate.orm.core;

	opens co.edu.uniquindio.agenciaViajes.application to javafx.fxml;
	//opens co.edu.uniquindio.agenciaViajes.controllers to javafx.fxml;
	opens co.edu.uniquindio.agenciaViajes.model;

	exports co.edu.uniquindio.agenciaViajes.model;
	exports co.edu.uniquindio.agenciaViajes.application;

}
