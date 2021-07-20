package Enigma.ParkingProject.repository;

import Enigma.ParkingProject.model.Appointment;

import java.util.List;

public interface IAppointmentDAL
{
    List<Appointment> getAllAppointments();

    Appointment getAppointmentById(int id);

    void addAppointment(Appointment appointment);

    void deleteAppointment(int id);

    List<Appointment> getAllAppointmentsByGuestId(int guestId);
}

