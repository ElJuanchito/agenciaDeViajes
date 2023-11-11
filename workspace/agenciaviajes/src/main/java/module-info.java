
open module co.edu.uniquindio.agenciaViajes {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.persistence;
	requires lombok;
	requires java.sql;
	requires org.hibernate.orm.core;
	requires transitive javafx.graphics;
	requires java.desktop;
	requires javafx.swing;
	requires javafx.media;
	requires AnimateFX;
	requires org.apache.pdfbox;

	exports co.edu.uniquindio.agenciaviajes.model;
	exports co.edu.uniquindio.agenciaviajes.application;
}
