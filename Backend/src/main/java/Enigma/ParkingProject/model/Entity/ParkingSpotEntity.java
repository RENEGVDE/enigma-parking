package Enigma.ParkingProject.model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "parkingspot")
public class ParkingSpotEntity {
    @Id
    @Column(name = "spotId")
    private String spotID;
    @Column(name = "occupied")
    private String occupied;

    public ParkingSpotEntity() {

    }

    public String getSpotID() {
        return spotID;
    }

    public void setSpotID(String spotID) {
        this.spotID = spotID;
    }

    public String getOccupied() {
        return occupied;
    }

    public void setOccupied(String occupied) {
        this.occupied = occupied;
    }

    public ParkingSpotEntity(String spotID, String occupied) {
        this.spotID = spotID;
        this.occupied = occupied;
    }
}
