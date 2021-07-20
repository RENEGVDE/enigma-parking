package Enigma.ParkingProject.controller;

import Enigma.ParkingProject.service.CSVService;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/CSV")
public class CSVController {

    @Autowired
    private CSVService csvService;

    @GetMapping()
    public void ScanCSV() throws IOException, CsvException {
        csvService.getCSv("c:/ParkingSpots.csv");
    }

}
