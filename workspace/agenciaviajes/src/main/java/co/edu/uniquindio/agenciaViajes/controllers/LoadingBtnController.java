package co.edu.uniquindio.agenciaViajes.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class LoadingBtnController implements Initializable {

	@FXML
	private SVGPath svg1;

	@FXML
	private SVGPath svg2;

	private Interpolator interpolator;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RotateTransition animacion = createRotateAnim(svg1, 0, 360);
		RotateTransition animacion2 = createRotateAnim(svg2, 360, 0);
		new ParallelTransition(animacion, animacion2).play();
	}

	private RotateTransition createRotateAnim(SVGPath svg, double from, double to) {
		RotateTransition animacion = new RotateTransition(Duration.millis(800), svg);
		animacion.setFromAngle(from);
		animacion.setToAngle(to);
		animacion.setInterpolator(getInterpolator());
		animacion.setCycleCount(-1);
		return animacion;
	}

	private Interpolator getInterpolator() {
		if (interpolator == null)
			interpolator = new Interpolator() {

				@Override
				protected double curve(double t) {
					return 2 * t * t * (3 - 2 * t);
				}
			};
		return interpolator;
	}

}
