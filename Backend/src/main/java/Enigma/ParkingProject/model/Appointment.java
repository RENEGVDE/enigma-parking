package Enigma.ParkingProject.model;

import javax.persistence.*;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appointmentId")
    private int appointmentId;
    @Column(name = "guestId")
    private int guestId;
    @Column(name = "emailEmployee")
    private String employeeEmail;
    @Column(name = "appointmentStartDate")
    private String appointmentStartDate;
    @Column(name = "appointmentEndDate")
    private String appointmentEndDate;


    public Appointment() { }


    public Appointment(int appointmentId, int guestId, String employeeEmail, String appointmentStartDate, String appointmentEndDate) {
        this.appointmentId = appointmentId;
        this.guestId = guestId;
        this.employeeEmail = employeeEmail;
        this.appointmentStartDate = appointmentStartDate;
        this.appointmentEndDate = appointmentEndDate;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getAppointmentStartDate() {
        return appointmentStartDate;
    }

    public void setAppointmentStartDate(String appointmentStartDate) {
        this.appointmentStartDate = appointmentStartDate;
    }

    public String getAppointmentEndDate() {
        return appointmentEndDate;
    }

    public void setAppointmentEndDate(String appointmentEndDate) {
        this.appointmentEndDate = appointmentEndDate;
    }
}
