
open module co.edu.uniquindio.agenciaserver {
	requires java.persistence;
	requires lombok;
	requires java.sql;
	requires org.hibernate.orm.core;
	requires java.desktop;
	requires javafx.swing;
	requires transitive javafx.graphics;
	requires jakarta.mail;
	requires org.hibernate.commons.annotations;

	exports co.edu.uniquindio.agenciaviajes.model;
	exports co.edu.uniquindio.agenciaserver.application;
}
