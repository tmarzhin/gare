package Gare;



public class Voyageur extends Thread {
	
	private EspaceVente ev;
	
	private EspaceQuai eq;
	
	/**
	 * l'id du voyageur
	 */
	private int idVoyageur;
	
	public Voyageur() {
		this.ev = null;
		this.eq = null;
		this.idVoyageur = 0;
	}
	
	public int getIdVoyageur() {
		return this.idVoyageur;
	}
	
	public Voyageur(EspaceVente ev,EspaceQuai eq) {
		this.ev = ev;
		this.eq = eq;
		
	}
	
	/**
	 * un voyageur achète un billet de train puis monte dans le train
	 */
	public void run() {
		ev.achatBillet();
		eq.monterDansTrain();
	}

}
