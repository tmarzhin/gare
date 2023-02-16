package Gare;

import java.util.Random;



public class Gare {
	
	private EspaceQuai quai;
	
	private EspaceVente guichet;
	
	private Train[] trains;
	
	private Voyageur[] voyageurs;
	
	private final int tempsArret = 800;
	
	private final int capacite = 250;
	
	/**
	 * fonction créant les voyageurs et les trains de la simulation
	 */
	public void genere() {
		
		for(int i =0;i<voyageurs.length;i++) {
			voyageurs[i] = new Voyageur(guichet,quai);
		}
		for(int i =0;i<trains.length;i++) {
			Random r = new Random();
			int vitesse = r.nextInt(300-50) + 50;
			trains[i] = new Train(vitesse,tempsArret,capacite,quai);
		}
	}
	
	/**
	 * fonction créant la gare de la simulation
	 */
	public Gare() {
		this.genere();
		this.guichet = new EspaceVente();
		this.quai = new EspaceQuai(10, 10,trains,guichet);
		for(int i =0;i<trains.length;i++) {
			trains[i].start();
		}
		for(int j =0;j<voyageurs.length;j++) {
			voyageurs[j].start();
		}
		
	}
	
	public static void main(String[] args) {
		new Gare();		
	}
	

}
