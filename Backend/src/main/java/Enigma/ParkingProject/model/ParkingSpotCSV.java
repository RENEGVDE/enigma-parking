package Enigma.ParkingProject.model;

import com.opencsv.bean.CsvBindByPosition;


public class ParkingSpotCSV {
    @CsvBindByPosition(position = 0)
    private String spotId;

    @CsvBindByPosition(position = 1)
    private String occupied;

    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public String getOccupied() {
        return occupied;
    }

    public void setOccupied(String occupied) {
        this.occupied = occupied;
    }


    public ParkingSpotCSV() {
    }

    public ParkingSpotCSV(String spotId, String occupied) {
        this.spotId = spotId;
        this.occupied = occupied;
    }

    @Override
    public String toString() {
        return "ParkingSpotCSV{" +
                "spotId='" + spotId + '\'' +
                ", occupied='" + occupied + '\'' +
                '}';
    }
}
