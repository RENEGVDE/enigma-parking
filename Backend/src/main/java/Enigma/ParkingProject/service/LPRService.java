package Enigma.ParkingProject.service;

import Enigma.ParkingProject.model.Account;
import Enigma.ParkingProject.serviceinterfaces.IAccountService;
import com.google.cloud.spring.vision.CloudVisionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class LPRService {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

    @Autowired
    private IAccountService accountService;

    public ArrayList<String> Scan(String Url) {
        String textFromImage = this.cloudVisionTemplate.extractTextFromImage(this.resourceLoader.getResource(Url));
        String[] textSeparated= textFromImage.split("\\s+");
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < textSeparated.length; i++){
            if (Pattern.matches("(([a-zA-Z]{3}[0-9]{3})|(\\w{2}-\\w{2}-\\w{2})|([0-9]{2}-[a-zA-Z]{3}-[0-9]{1})|([0-9]{1}-[a-zA-Z]{3}-[0-9]{2})|([a-zA-Z]{1}-[0-9]{3}-[a-zA-Z]{2}))", textSeparated[i])|| Pattern.matches("[a-zA-Z]{2}-[0-9]{3}-[a-zA-Z]", textSeparated[i]))
            {
                result.add(textSeparated[i]);
            }
        }
        return result;
    }

    public Account ScanAccount(String Url){
        for(Account account : accountService.getAccountList()) {
            for (String licensePlate : Scan(Url)) {
                if (account.getLicensePlate().equals(licensePlate)) {
                    return account;
                }
            }
        }
        return null;
    }
}
