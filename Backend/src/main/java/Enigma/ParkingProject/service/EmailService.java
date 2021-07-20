package Enigma.ParkingProject.service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String EmailTo, String GuestName, String Appointment) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(EmailTo);
        mail.setFrom("lucmoonen1@gmail.com");
        mail.setSubject("Guest arrived!");
        mail.setText( GuestName + " has arrived for the appointment at " + Appointment + ".");

        javaMailSender.send(mail);
    }
}
