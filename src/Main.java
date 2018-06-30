import lejos.hardware.BrickFinder;
import lejos.utility.Delay;
import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;

public class Main {

	public static void main(String[] args) 
    {
		int bouton;
		boolean StopProgramme = false;
		boolean Active = false;
		
		EV3LargeRegulatedMotor MotorA = new EV3LargeRegulatedMotor(BrickFinder.getLocal().getPort("A"));
	    EV3LargeRegulatedMotor MotorB = new EV3LargeRegulatedMotor(BrickFinder.getLocal().getPort("B"));
	    EV3LargeRegulatedMotor MotorC = new EV3LargeRegulatedMotor(BrickFinder.getLocal().getPort("C"));
	    EV3LargeRegulatedMotor MotorD = new EV3LargeRegulatedMotor(BrickFinder.getLocal().getPort("D"));

	    EV3ColorSensor ColorSensor = new EV3ColorSensor(BrickFinder.getLocal().getPort("S4")); 
    	
	    //Lumiere LampeA = new Lumiere(MotorA);
    	Lumiere PlafonnierA = new Lumiere(MotorB);
    	//Lumiere PlafonnierB = new Lumiere(MotorC);
    	//Lumiere LampeB = new Lumiere(MotorD);

    	CapteurLumiere CapteurIntensite = new CapteurLumiere(ColorSensor);
    	while (!StopProgramme) {
    		if (!Active) {
        		System.out.println("ESCAPE = Stop");
    	        System.out.println("ENTER = Activer");
    			Button.waitForAnyPress();
        		bouton = Button.getButtons();
    			switch (bouton) {			
    				case Button.ID_ESCAPE :
    					StopProgramme = true;
    					break;   				
    				case Button.ID_ENTER :
    					Active = true;
    					PlafonnierA.forward();
    					PlafonnierA.EclairageSelonPiece(50);
    					break;
    			}
    		}else {
    			System.out.println("GAUCHE = -5%");
    			System.out.println("DROIT = +5%");
    			System.out.println("ENTRER = PIECE");
    			System.out.println("HAUT = 100%");
                System.out.println("BAS = QUITTER");
                System.out.println("ECHAP = QUITTER");
                System.out.println("Intensite = " + PlafonnierA.getIntensite());
                Button.waitForAnyPress();
        		bouton = Button.getButtons();
    			switch(bouton){	
					case Button.ID_ENTER  :
						float intensitePiece = 0;
						int stop;
						do {
							intensitePiece = CapteurIntensite.getIntensite();
							PlafonnierA.EclairageSelonPiece(intensitePiece);
							stop = Button.getButtons();
						}while (stop == Button.ID_ENTER);
						break;
						
					case Button.ID_RIGHT : 
						PlafonnierA.brighter();  
						break;
					
					case Button.ID_LEFT : 
						PlafonnierA.starker();
						if (PlafonnierA.getIntensite()==0) {
							Active = false;
							StopProgramme = true;
						}
						break;
					
					case Button.ID_UP : 
						PlafonnierA.TurnOn();  
						break;
					
					case Button.ID_DOWN : 
						PlafonnierA.TurnOff();  
						Active = false;
						StopProgramme = true;
						break;
					
					case Button.ID_ESCAPE :
						Active = false;
						StopProgramme = true;
						break;
    			}
    		}
    	}
    }
}
