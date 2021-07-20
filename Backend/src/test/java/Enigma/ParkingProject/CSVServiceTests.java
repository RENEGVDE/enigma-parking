package Enigma.ParkingProject;

import Enigma.ParkingProject.model.Account;
import Enigma.ParkingProject.model.ParkingSpotCSV;
import Enigma.ParkingProject.service.CSVService;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;
@ActiveProfiles("test")
@SpringBootTest

public class CSVServiceTests {

    @Autowired
    CSVService service;
    @Test
   /* public void CSVScanSuccessTest() throws IOException, CsvException {

        CSVService service = new CSVService();

        String filename = "c:/ParkingSpots.csv";

        service.getCSv(filename);

        List<ParkingSpotCSV> parkingspots = service.getallSpots();

        Assertions.assertEquals(parkingspots.size(), 10);
    }
    */
    public void AssignGuestToSpot()
    {
        Account guest = new Account(4, "92-321-x","Gabriel","Brazil", "+31 0733821",false);
        service.assignSpot(guest.getAccountId());
        List<ParkingSpotCSV> spots = service.getallSpots();

        Assertions.assertEquals(spots.get(0).getOccupied(), "yes");
        Assertions.assertEquals(spots.get(0).getGuestId(), guest.getAccountId());


    }
}
