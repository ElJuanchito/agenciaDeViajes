package co.edu.uniquindio.agenciaserver.utils;

import java.util.Properties;

import co.edu.uniquindio.agenciaserver.model.Cliente;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailSender {
    private static EmailSender instance;

    public static EmailSender getInstance() {
        if (instance == null) instance = new EmailSender();
        return instance;
    }

    public void sendMail(Cliente cliente, String asunto, String reporte, String info) {
    	
    	StringBuilder contenido = generarMensajeCuerpo(cliente.getNombreCompleto(), reporte, info);
    	
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("pokeviajes39@gmail.com", "wioh ctbr wzcg dqcf");
            }
        };

        Session session = Session.getInstance(properties, authenticator);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("pokeviajes39@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(cliente.getEmail()));
            message.setSubject(asunto + " | PokeViajes");

            MimeBodyPart cuerpoMensaje = new MimeBodyPart();
            cuerpoMensaje.setContent(contenido, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(cuerpoMensaje);

            MimeBodyPart parteImagen = new MimeBodyPart();
            parteImagen.setDataHandler(new DataHandler(new FileDataSource("/src/main/resources/co/edu/uniquindio/agenciaserver/media/logo.png")));
            parteImagen.setHeader("Content-ID", "<imagen>");
            multipart.addBodyPart(parteImagen);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Correo enviado con Éxito");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    private StringBuilder generarMensajeCuerpo(String nombre, String reporte, String info) {
    	StringBuilder sbMsg1Solicitud = new StringBuilder();
		sbMsg1Solicitud.append("<div style='text-align: center;'><img src='cid:imagen' alt='Logo de PokeViajes' width='300' height='200'></div>");
		sbMsg1Solicitud.append("<b>");
		sbMsg1Solicitud.append(
				"<p style=\"margin: 30px 0 20px 0;padding:20px 0 0 0;font-family:'Open Sans',sans-serif;font-size:15px;\">");
		sbMsg1Solicitud.append("Estimado/a, ");
		sbMsg1Solicitud.append("<b>");
		sbMsg1Solicitud.append(nombre);
		sbMsg1Solicitud.append("</b>");
		sbMsg1Solicitud.append("<br><br>");
		sbMsg1Solicitud.append("Adjunto encontrará ");
		sbMsg1Solicitud.append(reporte);
		sbMsg1Solicitud.append(" de PokeViajes. Este informe contiene información detallada sobre");
		sbMsg1Solicitud.append(info);
		sbMsg1Solicitud.append(" y otros datos relevantes.<br>");
		sbMsg1Solicitud.append(
				"Para abrir el archivo adjunto, que se encuentra en formato PDF, asegúrese de tener instalado Adobe Acrobat Reader u otro programa compatible en su dispositivo.");
		sbMsg1Solicitud.append("Agradecemos su continuo apoyo y confianza en nuestros productos/servicios.");
		sbMsg1Solicitud.append("</p>");
		return sbMsg1Solicitud;
	}
}
