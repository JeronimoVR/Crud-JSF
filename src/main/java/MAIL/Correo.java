package MAIL;

import CONTROLLER.Productos;
import java.util.List;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;

public class Correo {

    private String to;
    private String subject;

    public Correo() {
        this.to = null;
        this.subject = null;
    }

    
    /**
     Metodo para enviar correos desde gmail
     Recibe una lista de productos para el cuerpo del correo
     */
    public void sendEmail(List<Productos> productos) {

        // Configuración de las propiedades
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465"); // Puerto para SSL
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465"); // Puerto estándar para SSL

        // Configurar la autenticación
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // Ingresa aquí tu dirección de correo y contraseña de Gmail
                return new PasswordAuthentication("", "");
            }
        });

        try {
            // Crear un objeto MimeMessage
            Message message = new MimeMessage(session);

            // Configurar dirección del remitente
            message.setFrom(new InternetAddress(""));

            // Configurar dirección del destinatario
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Configurar asunto
            message.setSubject(subject);
 
            StringBuilder bodyBuilder = new StringBuilder();
            bodyBuilder.append("Lista de productos:\n");

            for (Productos producto : productos) {
                bodyBuilder.append(producto.toString()).append("\n");
            }

            // Configurar cuerpo del mensaje
            message.setText(bodyBuilder.toString());

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("Correo electrónico enviado exitosamente.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
