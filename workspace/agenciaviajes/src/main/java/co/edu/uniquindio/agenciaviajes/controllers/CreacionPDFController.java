package co.edu.uniquindio.agenciaviajes.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import co.edu.uniquindio.agenciaviajes.exceptions.FXMLException;
import co.edu.uniquindio.agenciaviajes.model.Reserva;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreacionPDFController {

	private static CreacionPDFController instance;

	public static CreacionPDFController getInstance() {
		if (instance == null)
			instance = new CreacionPDFController();
		return instance;
	}

	public byte[] crearPDFReserva(Reserva reserva) throws FXMLException, IOException {
		Vista<Reserva> vista = Vista.buildView("pdfFactura");
		vista.cargarDato(reserva);
		vista.cargarIdioma();
		AnchorPane panel = (AnchorPane) vista.getParent();
		WritableImage snapshot = panel.snapshot(new SnapshotParameters(), null);
		File temp = new File("temp.png");
		PDDocument document = new PDDocument();
		PDPage page = new PDPage(new PDRectangle(1082, 733));
		document.addPage(page);
		ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", temp);
		PDImageXObject pdImage = PDImageXObject.createFromFile(temp.getAbsolutePath(), document);

		// Agregar la imagen al documento PDF
		try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
			contentStream.drawImage(pdImage, 1082, 733);
		}
		document.save(new File("reserva.pdf"));
		document.close();
		return Files.readAllBytes(new File("reserva.pdf").toPath());
	}

}
