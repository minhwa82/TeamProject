package member.db;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailDAO {



	public void SendEmail(String mem_email, String randomNum) {
	  

	        // Sender's email ID needs to be mentioned
	        String from = "hellomv2023@gmail.com";
	        // Recipient's email ID needs to be mentioned.
	        String to = mem_email;

	        // Assuming you are sending email from localhost
	        String host = "smtp.gmail.com";

	        // Get system properties
	        Properties properties = System.getProperties();

	        // Setup mail server
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", "587");
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");

	        // Get the Session object.// and pass username and password
	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
	                return new javax.mail.PasswordAuthentication("hellomv2023@gmail.com", "iyuhnonsttifitvo");
	            }
	        });

	        try {
	            // Create a default MimeMessage object.
	            MimeMessage message = new MimeMessage(session);

	            // Set From: header field of the header.
	            message.setFrom(new InternetAddress(from));

	            // Set To: header field of the header.
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	            // Set Subject: header field
	            message.setSubject("안녕화세요 회원가입 본인인증입니다.");

	            // Now set the actual message
	            message.setText("인증번호: "+randomNum+"\n\n인증번호를 정확하게 입력해주세요.");

	            // Send message
	            Transport.send(message);
	            System.out.println("이메일 인증코드 발송 완료");
	        } catch (MessagingException mex) {
	            mex.printStackTrace();
	        }
	}
	
	public String randomCode() {
		
		Random random = new Random();
		//String number = random.nextInt(1000000)+"";
		String code = "";
		for (int i = 0; i < 6; i++) {
			code += random.nextInt(10);
		}
		//System.out.println(code);
		    
		
		//System.out.println(number);
		return code;
	}
	
}
	

