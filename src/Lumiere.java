import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;

public class Lumiere {

	//Un moteur par Lumière
	private EV3LargeRegulatedMotor Lumiere;
	
	private float Intensite; //Pourcentage de puissance Moteur
	private float IntensiteMax; // Puissance maximale du moteur
	
	public Lumiere (EV3LargeRegulatedMotor Lumiere) {
		this.Lumiere = Lumiere;
		Intensite = 0;
		this.IntensiteMax = Lumiere.getMaxSpeed();
	}
	
	public float getIntensite() {
		return this.Intensite;
	}
	
	public void setIntensite(float a) {
		if (a<0) {
			a=0;
		}
		else if (a>100) {
			a=100;
		}
		this.Intensite = a;
		Lumiere.setSpeed(a * IntensiteMax/100);
	}
	
	public void forward() {
		this.Lumiere.forward();
		this.TurnOn();
	}
	public void brighter () {
		float val = (Intensite + 5);
		this.setIntensite(val);
	}
	
	public void starker () {
		float val = (Intensite - 5);
		this.setIntensite(val);
	}
	
	public void TurnOn() {
		float val = 100;
		this.setIntensite(val);
	}
	
	public void TurnOff() {
		float val = 0;
		this.setIntensite(val);
	}
	
	public void EclairageSelonPiece(float PourcentageLumiereExistante) {
		float val = (100 - PourcentageLumiereExistante);
		this.setIntensite(val);
		}
}
