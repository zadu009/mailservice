// Java Program to Create Rest Controller that
// Defines various API for Sending Mail

package de.ehandel.mailservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.IOException;

// Annotation
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
// Class
public class EmailController {

    @Autowired private EmailService emailService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/sendFinalMail")
    public String sendFinalMail(@ModelAttribute("emailDetails") EmailDetails emailDetails) {


            //JsonNode jsonNode = objectMapper.readTree(stringDetails);
            //EmailDetails details = objectMapper.readValue((DataInput) jsonNode, EmailDetails.class);
            emailService.sendFinalEmail(emailDetails);
            return "Vielen Dank für Ihre Bestellung. Bestellung wird von Gözde verarbeitet";



    }

    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailDetails details)
    {
        String status
                = emailService.sendSimpleMail(details);

        return status;
    }
}
