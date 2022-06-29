
package com.produsol.cadastro.grupo.util;

import javax.mail.*;
import javax.mail.internet.*;

import com.produsol.cadastro.grupo.properties.Propriedades;

import java.util.Properties;

public class SendEmail {
	
	Propriedades api = new  Propriedades();
    
public void Send(String name,String destEmail,String amount,String referencia, String entidade) throws Exception{
	
	String username= api.GetEnviromentVariables().get(3).replace("\"", "");
	String password= api.GetEnviromentVariables().get(4).replace("\"", "");
    Properties props = new Properties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.host", api.GetEnviromentVariables().get(5).replace("\"", ""));
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.starttls.required", "true");
    props.put("mail.smtp.port", api.GetEnviromentVariables().get(6).replace("\"", ""));
  
    String html=
			"<p align='justify'><b>Caro Cooperado,</b></p>"
					+ "<p>O seu processo de inscri&ccedil;&atilde;o est&aacute; quase conclu&iacute;do, queira por favor efectuar o pagamento da sua quota inicial nas coordenadas abaixo:</p>"
					+ "<p><b>Entidade:</b>" + entidade.replace("\"", "") + "</p>"
					+ "<p><b>Refer&ecirc;ncia de Cooperado:</b> "+ referencia.replace("\"", "") + "</p>"
					+ "<p><b>Valor:</b> "+ amount.replace("\"", "") + "</p>"
					+ "<br />"  
					+ "<p><b>COOPERATIVA DE CR&Eacute;DITO FAJE</b></p>"
					+ "<p><b>VAMOS POUPAR JUNTOS</b></p>"
					+ "<br />"  
					+ "<p align='center'>"
					+ "<img src='http://cooperafaje.com/wp-content/uploads/2018/04/LOGOTIPO-COOPERATIVA2-e1524224683973.jpg' width='150px' height='150' />"
					+ "</p>";
    Authenticator auth = new SMTPAuthenticator(username,password);
    Session mailSession = Session.getInstance(props, auth);
    mailSession.setDebug(true);
    MimeMessage message = new MimeMessage(mailSession);
    message.setSubject("Pagamento da Quota Inicial com referência nº: "+referencia);
    message.setContent(html, "text/html");
    message.setFrom(new InternetAddress(username));
    message.addRecipient(Message.RecipientType.TO,
         new InternetAddress(destEmail));
    Transport transport = mailSession.getTransport();
    transport.connect();
    transport.sendMessage(message,
        message.getRecipients(Message.RecipientType.TO));
    transport.close(); 
}

public void Notificar(String name, String destEmail) throws Exception
{
	Propriedades api = new  Propriedades();
	Properties props = new Properties();
	props.put("mail.transport.protocol", "smtp");
	props.put("mail.smtp.host", api.GetEnviromentVariables().get(5).replace("\"", ""));
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.starttls.required", "true");
	props.put("mail.smtp.port", api.GetEnviromentVariables().get(6).replace("\"", ""));
	String html = "<p align='justify'><b>Prezado(a),</b></p>"
			+ "<p>Prezado,</p>" + "<br />"
			+ "<p>Possui uma tarefa pendente no GingaBPM.</p>"
			+ "<p><b>Consultar o mais r&aacute;pido poss&iacute;vel.</b></p>"+ "<br />"
			+ "<p align='center'>"
			+ "</p>";
	
	Authenticator auth = new SMTPAuthenticator(api.GetEnviromentVariables().get(3).replace("\"", ""), api.GetEnviromentVariables().get(4).replace("\"", ""));
	Session mailSession = Session.getInstance(props, auth);
	mailSession.setDebug(true);
	MimeMessage message = new MimeMessage(mailSession);
	message.setSubject("COOPERA FAJE");
	message.setContent(html, "text/html");
	message.setFrom(new InternetAddress(api.GetEnviromentVariables().get(3).replace("\"", "")));
	message.addRecipient(Message.RecipientType.TO, new InternetAddress(destEmail));
	Transport transport = mailSession.getTransport();
	transport.connect();
	transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
	transport.close();
}

}
	
