package Enigma.ParkingProject.model;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Table(name ="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_ID")
    private int accountId;
    @Column(name = "licenseplate")
    private String licensePlate;
   @Column(name = "firstname")
    private String firstName;
   @Column(name = "lastname")
    private String lastName;
   @Column(name = "phonenumber")
    private String phoneNumber;
   @Column(name = "SmsEnable")
   private boolean contactViaWhatsapp;

    public Account(int accountId, String licensePlate, String firstName, String lastName, String phoneNumber, boolean contactViaWhatsapp) {
        this.accountId = accountId;
        this.licensePlate = licensePlate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.contactViaWhatsapp = contactViaWhatsapp;
    }

    public Account() { }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public boolean getContactViaWhatsapp() {
        return contactViaWhatsapp;
    }

    public void setContactViaWhatsapp(boolean contactViaSms) {
        this.contactViaWhatsapp = contactViaSms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", licensePlate='" + licensePlate + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", contactViaSms=" + contactViaWhatsapp +
                '}';
    }
}
