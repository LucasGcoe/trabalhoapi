package br.com.api.trabalhoIndividual.Services;



import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


@Configuration
@Service
public class EmailService {

	@Autowired
	UserService userService ;
	private JavaMailSender emailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSender emailSender) {
        this.setEmailSender(emailSender);
    }
	
	@Value("${spring.mail.host}")
	private String host;
	
	@Value("${spring.mail.port}")
	private Integer port;
	
	@Value("${spring.mail.username}")
	private String username;
	
	@Value("${spring.mail.password}")
	private String password;
	
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
		Properties prop = new Properties();
		emailSender.setHost(host);
		emailSender.setPort(port);
		emailSender.setUsername(username);
		emailSender.setPassword(password);
		prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
		emailSender.setJavaMailProperties(prop);
		return emailSender;
	}

	public static void envioEmailInativo() {
		// TODO Auto-generated method stub
		
	}

	public JavaMailSender getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}
}