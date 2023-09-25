package co.edu.uniquindio.agenciaViajes.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class PruebaController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private HBox box;

	@FXML
	private HBox box2;
	@FXML
	private URL location;

	@FXML
	private BorderPane ojo;

	@FXML
	private Circle cicrle;

	@FXML
	private Circle cicrle1;

	@FXML
	private ScrollPane scroll;

	@FXML
	void initialize() {
		Animation transition2 = configTransition(cicrle1);
		Animation transition = configTransition(cicrle);
		Animation transition3 = configTransition(ojo);

		ParallelTransition parallel = new ParallelTransition(transition, transition2);

		animateWithScroll(parallel, box, Duration.millis(150));
		animateWithScroll(transition3, box2, Duration.millis(100));
	}

	private ScaleTransition configTransition(Node node) {
		ScaleTransition transition = new ScaleTransition(Duration.millis(100), node);
		transition.setFromY(0);
		transition.setFromX(0);
		transition.setToX(1);
		transition.setToY(1);
		transition.setRate(-1);
		return transition;
	}

	private void animateWithScroll(Animation transition, Node node, Duration animDuration) {
		scroll.vvalueProperty().addListener((observable, oldValue, t) -> {
			Bounds bounds = scroll.sceneToLocal(node.localToScene(node.getBoundsInLocal()));
			double rate = transition.getRate();
			if (rate == 1 && (bounds.getMinY() < 0 || scroll.getViewportBounds().getMaxY() - bounds.getMaxY() < 0)) {
				transition.stop();
				transition.setRate(-1);
				transition.jumpTo(animDuration);
				transition.play();
			} else if (rate == -1 && bounds.getMinY() > 0
					&& scroll.getViewportBounds().getMaxY() - bounds.getMaxY() > 0) {
				transition.playFromStart();
			}
		});
	}
}
