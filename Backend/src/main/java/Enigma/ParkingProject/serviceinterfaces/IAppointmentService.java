package Enigma.ParkingProject.serviceinterfaces;

import Enigma.ParkingProject.model.Appointment;

import java.util.List;

public interface IAppointmentService {


    List<Appointment> getAllAppointments();

    Appointment getAppointmentById(int id);

    void addAppointment(Appointment appointment);

    void deleteAppointment(int id);

    Appointment ScanAppointment(int accountId);

    void getAllAppointmentsFromDeletedGuest(int guestId);
    List<Appointment> getAllAppointmentsFromGuest(int guestId);
}

