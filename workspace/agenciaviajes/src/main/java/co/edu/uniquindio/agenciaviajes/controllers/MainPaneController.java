package co.edu.uniquindio.agenciaviajes.controllers;

import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.services.Controllable;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class MainPaneController implements Controllable {

	private static MainPaneController instance;

	@FXML
	private Button closeBtn;

	@FXML
	private Label lblMessage;

	@FXML
	private BorderPane loadingLayer;

	@FXML
	private BorderPane mainPane;

	@FXML
	private BorderPane messageLayer;

	@FXML
	private SVGPath svg1;

	@FXML
	private SVGPath svg2;

	private Interpolator interpolator;

	private ParallelTransition animacionBtn;

	@Override
	public void preInicializar() {
		RotateTransition createRotateAnim = createRotateAnim(svg1, 0, 360);
		RotateTransition createRotateAnim2 = createRotateAnim(svg2, 360, 0);
		animacionBtn = new ParallelTransition(createRotateAnim, createRotateAnim2);
	}

	@FXML
	void closeAlertAction(ActionEvent event) {
		closeAlertActionA();
	}

	private void closeAlertActionA() {
		ejecutarProceso(() -> hidePane(messageLayer));
	}

	private void hidePane(BorderPane pane) {
		FadeTransition anim = new FadeTransition(Duration.millis(200), pane);
		anim.setFromValue(1);
		anim.setToValue(0);
		anim.setOnFinished(e -> pane.setDisable(true));
		anim.play();
	}

	private void showPane(BorderPane pane) {
		FadeTransition anim = new FadeTransition(Duration.millis(200), pane);
		pane.setDisable(false);
		anim.setFromValue(0);
		anim.setToValue(1);
		anim.play();
	}

	public void showAlert(String msg) {
		lblMessage.setText(msg);
		showPane(messageLayer);
	}

	public void ejecutarProceso(Runnable runnable) {
		new Thread(() -> {
			showPane(loadingLayer);
			animacionBtn.playFromStart();
			runnable.run();
			hidePane(loadingLayer);
			animacionBtn.stop();
		}).start();

	}

	public MainPaneController() {
		instance = this;
	}

	public static MainPaneController getInstance() {
		return instance;
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

	@Override
	public void updateLanguage(ResourceBundle bundle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub

	}

	public void setRoot(Parent value) {
		mainPane.setCenter(value);
	}

}
