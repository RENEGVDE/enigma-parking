package Enigma.ParkingProject;

import com.github.sarxos.webcam.*;
import Enigma.ParkingProject.controller.ScanController;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class ParkingProjectApplication implements WebcamMotionListener{

	@Autowired
	ScanController scanController;

	public ParkingProjectApplication() {
		WebcamMotionDetector detector = new WebcamMotionDetector(Webcam.getDefault());
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		detector.setInterval(300);
		detector.addMotionListener(this);
		detector.start();
	}

	int Count;
	@Override
	public void motionDetected(WebcamMotionEvent wme) {
		Count++;
		try {
			ImageIO.write(Webcam.getDefault().getImage(), "JPEG", new File("Screenshot.jpeg"));
			scanController.Scan();
			Thread.sleep(5000);
		}
		catch(IOException | AWTException | InterruptedException | CsvException e){ }
		System.out.println("Motion detected " + Count);
	}

	public static void main(String[] args) throws IOException {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ParkingProjectApplication.class);
		builder.headless(false).run(args);

		new ParkingProjectApplication();
		System.in.read(); // keep program open
	}

}
