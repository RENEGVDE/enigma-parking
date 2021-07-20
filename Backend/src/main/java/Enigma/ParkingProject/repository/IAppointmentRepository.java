package Enigma.ParkingProject.repository;

import Enigma.ParkingProject.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAppointmentRepository extends JpaRepository<Appointment, Integer> {
    Appointment getAppointmentByAppointmentId(int id);
    List<Appointment> getAppointmentsByGuestId(int id);
}
