package Enigma.ParkingProject.repository;

import Enigma.ParkingProject.model.Entity.ParkingSpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IParkingSpotRepository extends JpaRepository<ParkingSpotEntity, String> {
    public List<ParkingSpotEntity> getParkingSpotEntitiesByOccupied(String occupied);
}
