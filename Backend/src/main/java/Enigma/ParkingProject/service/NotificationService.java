package Enigma.ParkingProject.service;

import java.awt.*;

public class NotificationService {

    public void displayTray(String GuestName, String Appointment) throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("Guest Arrived", "Guest "+ GuestName +" has arrived for the appointment at " + Appointment +".", TrayIcon.MessageType.INFO);
    }
}
