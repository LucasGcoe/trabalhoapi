package br.com.api.trabalhoIndividual.Services;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.api.trabalhoIndividual.Entities.User;

@Configuration
@Service
public class EmailService {

	@Autowired
	UserService userService;
	private JavaMailSender emailSender;

	@Autowired
	public void setJavaMailSender(JavaMailSender emailSender) {
		this.emailSender = emailSender;
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

	public void envioEmailCadastro() { // é esse método que vamos copiar
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("apiserrag1@gmail.com");
			helper.setTo("lucasoliveiragcoe@gmail.com");
			helper.setSubject("Residente concluida!");

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n");
			builder.append("	<body>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("		<img src=\"cid:ecommerce\">\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("			<h1>Residente cadastrado(a) com sucesso!</h1>\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("			<h3>Bem vindo(a) Meu Trabalho</h3>\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<br/>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("			Em caso de erro, favor contatar o suporte: lucasoliveiragcoe@gmail.com");
			builder.append("		</div>\r\n");
			builder.append("	</body>\r\n");
			builder.append("</html>\r\n");

			helper.setText(builder.toString(), true);
			ClassPathResource path = new ClassPathResource("img/livros.png");
			helper.addInline("ecommerce", path);
			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public void envioEmailInativo() {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("apiserrag1@gmail.com");
			helper.setTo("lucasoliveiragcoe@gmail.com");
			helper.setSubject("Residente Inativo!");

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n");
			builder.append("	<body>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("		<img src=\"cid:ecommerce\">\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("			<h1>O Residente foi CANCELADO com Sucesso!</h1>\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("			<h3>Lamentamos sua despedida, esperamos que volte em breve</h3>\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<br/>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("			Caso não reconheça essa ação, favor contatar o suporte: apiserrag1@gmail.com");
			builder.append("		</div>\r\n");
			builder.append("	</body>\r\n");
			builder.append("</html>\r\n");

			helper.setText(builder.toString(), true);
			ClassPathResource path = new ClassPathResource("img/livros.png");
			helper.addInline("ecommerce", path);
			emailSender.send(mensagemCadastro);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void envioEmailHabilidadeRealizado() {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("apiserrag1@gmail.com");
			helper.setTo("lucasoliveiragcoe@gmail.com");
			helper.setSubject("Habilidade Realizado!");

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n");
			builder.append("	<body>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("		<img src=\"cid:ecommerce\">\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("			<h1> Sua Habilidade foi cadastrada com Sucesso!</h1>\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<br/>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("			Caso não reconheça essa ação, favor contatar o suporte: apiserrag1@gmail.com");
			builder.append("		</div>\r\n");
			builder.append("	</body>\r\n");
			builder.append("</html>\r\n");

			helper.setText(builder.toString(), true);
			ClassPathResource path = new ClassPathResource("img/livros.png");
			helper.addInline("ecommerce", path);
			emailSender.send(mensagemCadastro);
		}

		catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
