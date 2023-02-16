package Gare;

public class EspaceQuai {
	
	/**
	 * nombre de voies max de l'espace quai 
	 * nbVoies est une variable partagée par l'ensemble des trains
	 */
	private int nbVoies;
	
	/**
	 * nombre de voies non occupées par des trains
	 */
	private int nbVoiesLibres;
	
	/**
	 * liste des trains stationnés en gare 
	 * trains est un objet partagé par l'ensemble des voyageurs
	 */
	private Train[] trains;
	
	/**
	 * billeterie li�e au quai	
	 */
		private EspaceVente ev;
	

	
	public EspaceQuai() {
		this.nbVoies = 10;
		this.nbVoiesLibres = 10;
		this.trains = new Train[10];
		this.ev = null;
	}
	
	public EspaceQuai(int nbVoies, int nbVoiesLibres, Train[]trains,EspaceVente ev) {
		if(nbVoiesLibres<=nbVoies) {
		this.nbVoies = nbVoies;
		this.nbVoiesLibres = nbVoiesLibres;
		this.trains = trains;
		this.ev = ev;
		}
	}
	
	public int getNbVoies() {
		return this.nbVoies;
	}
	
	public int NbVoiesLibres() {
		return this.nbVoiesLibres;
	}
	
	public void incremNbVoiesLibres() {
		this.nbVoiesLibres++;
	}
	
	public void decremNbVoiesLibres() {
		this.nbVoiesLibres--;
	}
	
	public Train[] getTrains() {
		return this.trains;
	}
	/**
	 * 
	 * @param train le train quitte la gare
	 * simule le d�part du train de la gare
	 */
	public void departTrain(Train train) {
		for(int i=0;i<trains.length;i++) {
			if(trains[i].equals(train)) {
				trains[i]=null;
				System.out.println("Le train "+Thread.currentThread().getName()+"stationn� voie"+i+" quitte la gare");
			}	
		}
		incremNbVoiesLibres();
		notifyAll();
		
		
	}
	
	/**
	 * 
	 * @param train le train qui arrive en gare
	 * fonction simulant l'arriv�e d'un train dans la gare
	 */
	public void arriveeTrain(Train train) {
		while(this.nbVoiesLibres<=0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i=0;i<trains.length;i++) {
			if(trains[i]==null) {
				trains[i] = train;
				System.out.println("Un train "+Thread.currentThread().getName()+" arrive en gare par la voie " + i);
			}
		}
		this.decremNbVoiesLibres();
		this.ev.incremBillets(train.getCapaciteMax());
		notifyAll();
		
	}
	
	/**
	 * 
	 * @param train le train qui accueille un voyageur suppl�mentaire
	 * fonction incr�mentant la capacit� du train quand un voyageur monte � bord
	 */
	public void monterDansTrain() {
		for(int i=0;i<trains.length;i++) {
			while(trains[i].nbPlacesLibres()<=0) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			trains[i].setCapacite(1);
			System.out.println("Un voyageur "+	Thread.currentThread().getName()+" monte dans le train de la voie "+i);
			notifyAll();
		}
		
	}
	/**
	 * 
	 * @param train
	 * un voyageur descend du train pass� en param�tre
	 */
	public void descendreDuTrain(Train train) {
		for(int i=0;i<trains.length;i++) {
			if(trains[i].equals(train)) {
				train.setCapacite(-1);
				notifyAll();
			}
		}
		
	}
}
