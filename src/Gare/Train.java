package Gare;
import java.util.Random;



public class Train extends Thread{


	/**
	 * vitesse du train en km/h (doit être compris entre 50 et 300 km/h)
	 */
	private int vitesseTrain;

	/**
	 * temps d'arrêt du train ensecondes
	 */
	private int arretTrain;

	/**
	 * capacité maximum du train (ne doit pas être dépassé)
	 */
	private int capaciteMax;

	/**
	 * le nombre de voyageurs actuellement dans le train
	 */
	private int capacite;

	/**
	 * le quai contenant la voie oû le train s'arrête
	 */
	private EspaceQuai quai;


	public Train() {
		Random r = new Random();
		this.vitesseTrain = r.nextInt(300-50) + 50;
		this.arretTrain = 300;
		this.capacite = 0;
		this.capaciteMax = 100;
		this.quai = null;
	}

	public Train(int vitesse,int arret,int capaciteMax,EspaceQuai quai) {
		if(!((vitesseTrain >300)&&(vitesseTrain<50))) {
			if(capacite<=capaciteMax) {
				this.vitesseTrain = vitesse;
				this.arretTrain = arret;
				this.capaciteMax = capaciteMax;
				this.quai = quai;		
			}
		}
	}

	/**
	 * 
	 * @return la vitesse du train
	 */
	public int getVitesse() {
		return this.vitesseTrain;
	}

	/**
	 * 
	 * @return le temps d'arrêt du train
	 */
	public int getArret() {
		return this.arretTrain;
	}

	/**
	 * 
	 * @return la capcité maximum du train
	 */
	public int getCapaciteMax() {
		return this.capaciteMax;
	}

	/**
	 * 
	 * @return le nombre de voyageurs dans le train
	 */
	public int getCapacite() {
		return this.capacite;
	}

	/**
	 * 
	 * @return le nombre de places restantes dans le train
	 */
	public int nbPlacesLibres() {	
		return getCapaciteMax() - getCapacite();
	}

	/**
	 * 
	 * @param newcapacite incremente la capacite du train
	 */
	public void setCapacite(int newcapacite) {
		if(this.capacite+newcapacite<=this.capaciteMax) {
			this.capacite= this.capacite+newcapacite;
		}
	}
	
	/**
	 * 
	 * @param newcapacite decremente la capacite du train
	 */
	public void decremCapacite(int newcapacite) {
		if(this.capacite-newcapacite>=0) {
			this.capacite= this.capacite-newcapacite;
		}
	}

	/**
	 * crée un nouveau train
	 * simule le temps d'arrivée du train en gare
	 * simule le temps d'arrêt du train en gare
	 */
	public void run() {
		for(;;) {
			Train train = new Train();
			try {
				Thread.sleep(10000 / train.vitesseTrain);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			quai.arriveeTrain(train);
			try {
				Thread.sleep(train.arretTrain);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			quai.departTrain(train);
		}
	}

}
