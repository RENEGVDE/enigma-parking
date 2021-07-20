package Enigma.ParkingProject.controller;

import Enigma.ParkingProject.model.Account;
import Enigma.ParkingProject.model.Appointment;
import Enigma.ParkingProject.service.*;
import Enigma.ParkingProject.serviceinterfaces.IAppointmentService;
import Enigma.ParkingProject.serviceinterfaces.ICSVService;
import com.github.sarxos.webcam.Webcam;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/camera")
public class ScanController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private LPRService lprService;

    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private ICSVService csvService;

    @GetMapping("/getStatusCamera")
    public int getStatusCamera () {
        try{
            Webcam w = Webcam.getDefault();
            if (w.getLock().isLocked())
                return 1;
            return 0;
        }
        catch (Exception exception) {
            return 0;
        }
    }

    @GetMapping("/LPR")
    public void Scan () throws AWTException, IOException, CsvException {

        NotificationService notificationService = new NotificationService();
        SmsService smsService = new SmsService();
        WhatsappService whatsapp = new WhatsappService();
        String httpcar = "file:Screenshot.jpeg";

       if(lprService.Scan(httpcar) != null) {
           csvService.getCSv("c:/ParkingSpots.csv");
           Account account = lprService.ScanAccount(httpcar);
           Appointment appointment = appointmentService.ScanAppointment(lprService.ScanAccount(httpcar).getAccountId());
           List<Appointment> guestsappointments = appointmentService.getAllAppointmentsFromGuest(account.getAccountId());
           if(guestsappointments != null) {
               notificationService.displayTray(account.getFirstName() + " " + account.getLastName(), appointment.getAppointmentStartDate());
           }
           emailService.sendEmail(appointment.getEmployeeEmail(), account.getFirstName()+" "+account.getLastName(), appointment.getAppointmentStartDate());
           boolean available = csvService.checkForAvailableSpots();
           if (available)
           {
               if(account.getContactViaWhatsapp() == false) {
                   smsService.SendSmsParkingAvailable(account.getPhoneNumber(), account.getFirstName() + " " + account.getLastName());
               }
               else {
                   whatsapp.WhatsappParkingAvailable(account.getPhoneNumber(), account.getFirstName() + " " + account.getLastName());
               }
           }
           else
           {
               if(account.getContactViaWhatsapp() == false) {
                   smsService.SendSmsParkingFull(account.getPhoneNumber(), account.getFirstName() + " " + account.getLastName());
               }
               else {
                   whatsapp.WhatsappParkingFull(account.getPhoneNumber(), account.getFirstName() + " " + account.getLastName());
               }
           }
           csvService.ConvertToCSV();

        }
    }
}
