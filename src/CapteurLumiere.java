import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.BaseSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;

public class CapteurLumiere {
	private EV3ColorSensor capteur;
	
	public CapteurLumiere(EV3ColorSensor Capteur) {
		this.capteur = Capteur;
	}
	
	public float getIntensite() {
		float result = 0;
		SampleProvider SP = this.capteur.getAmbientMode(); 
		int Ssize = SP.sampleSize();
		float[] sample = new float[Ssize];
		this.capteur.getAmbientMode().fetchSample(sample, 0);
		for (int i = 0; i < Ssize; i++) {
	        result = sample[i]*100;
	      }
		return result;
	}
}
