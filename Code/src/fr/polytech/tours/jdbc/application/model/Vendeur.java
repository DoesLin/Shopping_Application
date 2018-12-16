package fr.polytech.tours.jdbc.application.model;

/**
 * Classe permettant de gerer des vendeurs.
 * 
 * @author Lin et Moutas Ribeiro
 *
 */
public class Vendeur extends Personne{
	
	/**
	 * Attributs
	 */
	/**
	 * IDVendeur : attribut de type int, identifiant d'un vendeur.
	 */
	private int IDVendeur;
	/**
	 * IDPersonne : attribut de type Personne, permet de avoir les informations
	 * personnelles de cette personne.
	 */
	private Personne IDPersonne;
	
	public Vendeur(){
		this(-1, null);
	}
	public Vendeur(int iDVendeur){
		this(iDVendeur, null);
	}
	
	public Vendeur(Personne iDPersonne) {
		this.IDPersonne = iDPersonne;
	}
	
	public Vendeur(int iDVendeur, Personne iDPersonne) {
		this.IDVendeur = iDVendeur;
		this.IDPersonne = iDPersonne;
	}
	
	public Vendeur(int iDVendeur, String iDPersonne, String nom, String prenom, Adresse adresse){
		super(iDPersonne, nom, prenom, adresse);
		this.IDVendeur = iDVendeur;
	}
	
	/**
	 * Les accesseur et mutateurs des attributs de la classe.
	 */
	public int getIDVendeur() {
		return IDVendeur;
	}
	public void setIDVendeur(int iDVendeur) {
		IDVendeur = iDVendeur;
	}

	public String getIDPersonne() {
		return IDPersonne.getIDPersonne();
	}

	public void setIDPersonne(Personne iDPersonne) {
		IDPersonne = iDPersonne;
	}	
}
