package ec.edu.ups.proyecto.business;

import java.util.Properties;
import java.util.Random;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author Juan
 */
@Stateless
public class CorreoON {
	private static final String senderEmail = "appdisup@gmail.com";//change with your sender email
	  private static final String senderPassword = "12345UPS";//change with your sender password
	  /**
	   * El primer parametro es para quien se envia el correo 
	   * El parametro title es para enviar el titulo del correo
	   * El parametro html es donde se manda la informacion para el cuerpo del correo.
	   * @param to
	   * @param title
	   * @param html
	   * @throws MessagingException
	   */
	  public static void sendAsHtml(String to, String title, String html) throws MessagingException {
	      System.out.println("Sending email to " + to);

	      Session session = createSession();

	      //create message using session
	      MimeMessage message = new MimeMessage(session);
	      prepareEmailMessage(message, to, title, html);

	      //sending message
	      Transport.send(message);
	      System.out.println("Satisfactorio");
	  }
	  /**
	   * Recibe la session del mensaje previamente creado
	   * A quien se le envia el correo, tambien se le coloca el titulo y el cuerpo del correo
	   * @param message
	   * @param to
	   * @param title
	   * @param html
	   * @throws MessagingException
	   */
	  private static void prepareEmailMessage(MimeMessage message, String to, String title, String html)
	          throws MessagingException {
	      message.setContent(html, "text/html; charset=utf-8");
	      message.setFrom(new InternetAddress(senderEmail));
	      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	      message.setSubject(title);
	  }
	  
	  /**
	   * Se envia un correo realizando el protocolo de transferencia simple
	   * En este caso estamos utilizando el servicio de gmail
	   * Realiza el envio mediante el puerto 587
	   * @return
	   */
	  private static Session createSession() {
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");//Outgoing server requires authentication
	      props.put("mail.smtp.starttls.enable", "true");//TLS must be activated
	      props.put("mail.smtp.host", "smtp.gmail.com"); //Outgoing server (SMTP) - change it to your SMTP server
	      props.put("mail.smtp.port", "587");//Outgoing port

	      Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	          protected PasswordAuthentication getPasswordAuthentication() {
	              return new PasswordAuthentication(senderEmail, senderPassword);
	          }
	      });
	      return session;
	  }
	  
	  /**
	   * Se genera una constrasena aleatoria para ser usada en el envio de correos 
	   * cuando se crea un usuario, se envia al correo asociado al cliente.
	   * @return
	   */
	  public String contrasenaAleatoria() {
			 int length = 10;
		        String mayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
		        String minusculas = "abcdefghijklmnopqrstuvwxyz"; 
		        String numeros = "0123456789"; 

		        String finalString = mayusculas + numeros + minusculas; 

		        Random random = new Random(); 

		        //char[] password = new char[length]; 
		        String pswd = "";

		        for (int i = 0; i < length; i++) 
		        { 
		            //password[i] = finalString.charAt(random.nextInt(finalString.length()));
		            pswd+=(finalString.charAt((int)(Math.random() * finalString.length())));

		        } 
		        System.out.println(pswd);
		        return pswd;
		        
		}
}
