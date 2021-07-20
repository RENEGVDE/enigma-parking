package Enigma.ParkingProject.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class WhatsappService {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC6416fb83cd021d68e6017edaf49af703";
    public static final String AUTH_TOKEN = "323b9d7e30c2d55993487ae90e48bc44";

    public void WhatsappParkingFull(String WhatsappTo,String GuestName) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:" + WhatsappTo),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                "Hello " + GuestName + ", welcome to Sioux! The parking at Sioux is full. But you can to try to park at {place}.")
                .create();

        System.out.println(message.getSid());
    }

    public void WhatsappParkingAvailable(String WhatsappTo,String GuestName) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:" + WhatsappTo),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                "Hello " + GuestName + ", welcome to Sioux! You can park at Sioux parking.")
                .create();

        System.out.println(message.getSid());
    }
}
