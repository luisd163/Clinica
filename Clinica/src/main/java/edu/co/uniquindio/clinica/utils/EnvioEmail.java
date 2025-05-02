package edu.co.uniquindio.clinica.utils;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class EnvioEmail {

    /**
     * Método que envia un correo electronico
     * @param destinatario correo al que va enviado el correo electronico
     * @param asunto asunto del correo
     * @param mensaje mensaje que contiene el correo
     */
    public static void enviarNotificacion(String destinatario, String asunto, String mensaje) {
        Email email = EmailBuilder.startingBlank()
                .from("tilininsanofreefire666@gmail.com")
                .to(destinatario)
                .withSubject(asunto)
                .withPlainText(mensaje)
                .buildEmail();

        try (Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "tilininsanofreefire666", "ijro caef wihd umkc")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {


            mailer.sendMail(email);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
