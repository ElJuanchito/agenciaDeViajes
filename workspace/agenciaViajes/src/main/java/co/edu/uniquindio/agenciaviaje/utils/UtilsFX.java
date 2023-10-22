/**
 * 
 * @author ElJuancho
 */
package co.edu.uniquindio.agenciaviaje.utils;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.uniquindio.agenciaviaje.model.Imagen;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * 
 * @author ElJuancho
 */
public class UtilsFX {

	public static List<Image> cargarImagenes(List<Imagen> listaImagenes) {
		return listaImagenes.stream().map(Imagen::getImage).collect(Collectors.toList());
	}

	public static void setAsNumberTextfield(TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tf.setText(newValue.replaceAll("[^\\d]", ""));
					abrirContextMenu(tf, "Este campo solo puede tener numeros");
				}
			}

		});
	}

	public static void setAsNameTextField(TextField tf) {
		tf.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("[a-zA-Z\\s]*")) {
				tf.setText(newValue.replaceAll("[^a-zA-Z\\s]", ""));
				abrirContextMenu(tf, "Este campo solo puede contener letras y espacios");
			}
		});
	}

	public static void setAsHourTextField(TextField tf) {
		tf.setText("00:00");

		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches(":|[0-9]{1,2}:[0-9]{0,2}")) {
					tf.setText("00:00");
					abrirContextMenu(tf, "Ingresa la hora en formato 'HH:mm'");
				}
			}
		});
	}

	private static void abrirContextMenu(Node nodo, String msg) {
		final ContextMenu menu = new ContextMenu();
		menu.setStyle("-fx-text-fill: black;");
		menu.getItems().add(new MenuItem(msg));
		menu.show(nodo, Side.BOTTOM, 0, 0);

		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), evento -> {
			menu.hide();
		}));
		timeline.play();
	}

	public static void setAsIntegerTextfield(TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.isEmpty())
					return;
				try {
					Integer.parseInt(tf.getText());
				} catch (Exception e) {
					observable.removeListener(this);
					tf.setText(oldValue);
					observable.addListener(this);
					abrirContextMenu(tf, "Este campo solo puede tener numeros enteros");
				}
			}
		});
	}

	public static void setMaxLengthHourSize(TextField tf, int tamanio) {
		setMaximumTextSize(tf, tamanio, "Ingresa la hora en formato 'HH:mm'");
	}

	public static void setMaximumTextSize(TextField tf, int tamanio, String msg) {
		tf.setTextFormatter(new TextFormatter<String>(cambio -> {
			if (cambio.isContentChange()) {
				if (cambio.getControlNewText().length() > tamanio) {
					abrirContextMenu(tf, msg);
					return null;
				}
			}
			return cambio;
		}));
	}
}
