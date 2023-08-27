// Java Program to Illustrate Creation Of
// Service implementation class

package de.ehandel.mailservice;

// Importing required classes
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.time.Clock;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

// Annotation
@Service
// Class
// Implementing EmailService interface
public class EmailServiceImpl implements EmailService {

    @Autowired private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    final Configuration configuration;

    Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    public EmailServiceImpl(Configuration configuration, JavaMailSender javaMailSender) {
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
    }


    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details)
    {

        // Try block to check for exceptions
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject("Vielen Dank für Ihren Einkauf");
            helper.setTo(details.getRecipient());
            String emailContent = getEmailContent(details);
            helper.setText(emailContent, true);
            //File imageFile = new File(getClass().getClassLoader().getResource("templates/images/favicon.png").getFile());
            //FileSystemResource res = new FileSystemResource(imageFile);
            //helper.addInline("identifier1234", res);
            javaMailSender.send(mimeMessage);
            logger.info("Versende Email an: "+ details.getRecipient());
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {

            return "Error while Sending Mail" + e.getMessage();
        }
    }

    public String sendFinalEmail(EmailDetails details)
    {

        // Try block to check for exceptions
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject("Bestellung von");
            helper.setTo(new String[]{"kirmi@gmx.de", "s.durrani@gmx.de"});
            String emailContent = getEmailContent(details);
            helper.setText(emailContent, true);
            StringBuilder result = new StringBuilder();

            for(Order order: details.getOrders())
            {
                result.append(", Produktname: ").append(order.getName())
                        .append(", Preis: ").append(order.getPrice())
                        .append(", Anzahl: ").append(order.getQuantity())
                        .append("\n");
            }
            helper.setText(result +" \n von " +
                    details.getUser().getFirstname().toString() + " " + details.getUser().getName().toString()
            +" aus "+ details.getUser().getCity().toString() + "\n Gesamtpreis: " + details.getCheckoutprice());
            //File imageFile = new File(getClass().getClassLoader().getResource("templates/images/favicon.png").getFile());
            //FileSystemResource res = new FileSystemResource(imageFile);
            //helper.addInline("identifier1234", res);
            javaMailSender.send(mimeMessage);
            logger.info("Versende Email an: Gözde");
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {

            return "Error while Sending Mail" + e.getMessage();
        }
    }


    String getEmailContent(EmailDetails emailDetails) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("emailDetails", emailDetails);
        if(!CollectionUtils.isEmpty(emailDetails.getOrders())){
            double sum=0;
            for(Order order : emailDetails.getOrders()){
                sum=sum + Double.valueOf(order.getSalePrice()) * Double.valueOf(order.getQuantity());
            }

            emailDetails.setCheckoutprice(sum);
        }
        configuration.getTemplate("email.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }

}
