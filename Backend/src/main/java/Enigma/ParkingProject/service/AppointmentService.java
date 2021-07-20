package Enigma.ParkingProject.service;

import Enigma.ParkingProject.model.Appointment;
import Enigma.ParkingProject.repository.IAppointmentDAL;
import Enigma.ParkingProject.serviceinterfaces.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {

    @Autowired

    private IAppointmentDAL appointmentDAL;


    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentDAL.getAllAppointments();
    }

    @Override
    public Appointment getAppointmentById(int id) {
        return appointmentDAL.getAppointmentById(id);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        appointmentDAL.addAppointment(appointment);
    }

    @Override
    public void deleteAppointment(int id) {
        appointmentDAL.deleteAppointment(id);
    }

    @Override
    public Appointment ScanAppointment(int accountId){
        for (Appointment appointment: getAllAppointments()) {
            if (appointment.getGuestId() == accountId){
                return appointment;
            }
        }
        return null;
    }

    @Override
    public void getAllAppointmentsFromDeletedGuest(int guestId) {
        List<Appointment> appointments = appointmentDAL.getAllAppointmentsByGuestId(guestId);
        for (Appointment a : appointments)
        {
            appointmentDAL.deleteAppointment(a.getAppointmentId());
        }
    }

    @Override
    public List<Appointment> getAllAppointmentsFromGuest(int guestId) {
        List<Appointment> appointments = appointmentDAL.getAllAppointmentsByGuestId(guestId);
        return appointments;
    }

}
