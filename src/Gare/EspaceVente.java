package Gare;


public class EspaceVente {
	
	/**
	 * nombre de billets disponibles dans la billetterie
	 */
	private int nbBillets;
	
	/**
	 * Tableau contenant les trains en gare
	 */
		private Train[] trains;
	
	public EspaceVente() {
		this.nbBillets = 500;
		this.trains = new Train[10];
	}
	
	public EspaceVente(int nbBillet) {
		this.nbBillets = nbBillet;
	}
	
	/**
	 * incr�mente le nombre de billets disponibles
	 * @param newNbBillets
	 */
	public void incremBillets(int newNbBillets) {
		this.nbBillets = nbBillets + newNbBillets;
	}
	
	/**
	 * d�cr�mente le nombre de billets disponibles
	 * @param newNbBillets
	 */
	public void decremBillets(int newNbBillets) {
		this.nbBillets = nbBillets - newNbBillets;
	}
	
	/**
	 * un voyageur ach�te un billet de train
	 */
	public synchronized void achatBillet() {
		while(nbBillets<=0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		nbBillets--;
		notifyAll();
		for(int i=0;i<trains.length;i++) {
			if(trains[i].nbPlacesLibres()>0) {
				trains[i].decremCapacite(1);
				System.out.println("Un voyageur "+Thread.currentThread().getName()
						+" a achet� un billet de train stationn� voie "+i);
			}
		}
	}
}


