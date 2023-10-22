package co.edu.uniquindio.agenciaviajes.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import co.edu.uniquindio.agenciaviajes.exceptions.ImagenNoObtenidaException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Table(name = "imagenes")
@Data
public class Imagen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	@NonNull
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private byte[] imageBytes;

	public static Imagen createImage(Image image) throws ImagenNoObtenidaException {
		Imagen imagen = new Imagen();
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", baos);
			imagen.setImageBytes(baos.toByteArray());
		} catch (IOException e) {
			throw new ImagenNoObtenidaException("La imagen no pudo ser obtenida", e);
		}
		return imagen;
	}

	public Image getImage() {
		ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
		return new Image(bais);
	}
}
