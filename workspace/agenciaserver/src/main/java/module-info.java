
open module co.edu.uniquindio.agenciaserver {
	requires java.persistence;
	requires lombok;
	requires java.sql;
	requires org.hibernate.orm.core;
	requires java.desktop;
	requires javafx.swing;
	requires transitive javafx.graphics;

	exports co.edu.uniquindio.agenciaserver.model;
	exports co.edu.uniquindio.agenciaserver.application;
}
