package Enigma.ParkingProject.repository.DAL;

import Enigma.ParkingProject.model.Appointment;

import Enigma.ParkingProject.repository.IAppointmentDAL;
import Enigma.ParkingProject.repository.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppointmentDAL implements IAppointmentDAL {

    @Autowired
    private IAppointmentRepository repo;

    @Override
    public List<Appointment> getAllAppointments() {
        return repo.findAll();
    }

    @Override
    public Appointment getAppointmentById(int id) {
        return repo.getAppointmentByAppointmentId(id);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        repo.save(appointment);
    }

    @Override
    public void deleteAppointment(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<Appointment> getAllAppointmentsByGuestId(int guestId) {
        return repo.getAppointmentsByGuestId(guestId);
    }
}
