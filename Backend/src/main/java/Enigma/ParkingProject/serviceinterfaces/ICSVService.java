package Enigma.ParkingProject.serviceinterfaces;

import Enigma.ParkingProject.model.ParkingSpotCSV;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.List;

public interface ICSVService {
    void getCSv(String filename) throws IOException, CsvException;
    List<ParkingSpotCSV> getallSpots();
    boolean checkForAvailableSpots();
    void ConvertToCSV() throws IOException;


}
