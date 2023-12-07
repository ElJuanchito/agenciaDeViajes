package co.edu.uniquindio.agenciaviajes.viewcontrollers;

import java.util.ResourceBundle;

import co.edu.uniquindio.agenciaviajes.services.Controllable;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class MainPaneController implements Controllable {

	private static MainPaneController instance;

	@FXML
	private Label lblMessage, lblMessage2;

	@FXML
	private BorderPane loadingLayer, mainPane, messageLayer, messageLayerAccept;

	@FXML
	private SVGPath svg1, svg2;

	private Interpolator interpolator;

	private ParallelTransition animacionBtn;

	private Runnable runnableAccept;

	@Override
	public void preInicializar() {
		RotateTransition createRotateAnim = createRotateAnim(svg1, 0, 360);
		RotateTransition createRotateAnim2 = createRotateAnim(svg2, 360, 0);
		animacionBtn = new ParallelTransition(createRotateAnim, createRotateAnim2);
	}

	@FXML
	void closeAlertEvent(ActionEvent event) {
		closeAlertAction();
	}

	@FXML
	void closeAlertAcceptEvent(ActionEvent event) {
		closeAlertAcceptAction();
	}

	@FXML
	void acceptAlertEvent(ActionEvent event) {
		acceptAlertAction();
	}

	private void acceptAlertAction() {
		ejecutarProceso(() -> {
			hidePane(messageLayerAccept);
			if (runnableAccept != null)
				runnableAccept.run();
		});
	}

	private void closeAlertAcceptAction() {
		ejecutarProceso(() -> hidePane(messageLayerAccept));
	}

	private void closeAlertAction() {
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
		Platform.runLater(() -> {
			lblMessage.setText(msg);
			showPane(messageLayer);
		});
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

	public void ejecutarProcesoDoble(Runnable runnable, Runnable atEnd) {
		new Thread(() -> {
			showPane(loadingLayer);
			animacionBtn.playFromStart();
			runnable.run();
			hidePane(loadingLayer);
			animacionBtn.stop();
			atEnd.run();
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

	public void showAlertAccept(String msg, Runnable runnableAccept) {
		this.runnableAccept = runnableAccept;
		Platform.runLater(() -> {
			lblMessage2.setText(msg);
			showPane(messageLayerAccept);
		});
	}

}
