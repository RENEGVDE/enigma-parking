package Enigma.ParkingProject.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SmsService {
    public static final String ACCOUNT_SID = "ACe45c96c2b43919a3173b4caf415abc87";
    public static final String AUTH_TOKEN = "e50811fa1a0fe13b579149831af7231d";

    public void SendSmsParkingFull(String SmsTo, String GuestName){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(SmsTo),
                "MGfdded448aafea6c48572679dd93fa642",
                "Hello " + GuestName + ", welcome to Sioux! The parking at Sioux is full.")
                .create();

        System.out.println(message.getSid());
    }

    public void SendSmsParkingAvailable(String SmsTo, String GuestName){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(SmsTo),
                "MGfdded448aafea6c48572679dd93fa642",
                "Hello " + GuestName + ", welcome to Sioux! You can park at Sioux parking.")
                .create();

        System.out.println(message.getSid());
    }
}
