package fr.polytech.tours.jdbc.application.model;
/**
 * Classe categorie permettant de gerer les categories des ventes proposes.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class Categorie {
	private int IDCategorie;
	private String Nom;
	private String DescriptionCategorie;
	/**
	 * Constructeur par default.
	 */
	public Categorie(){
		this(-1, "","");
	}
	
	public Categorie(String nom, String descriptionCategorie) {
		this.IDCategorie = -1;
		this.Nom = nom;
		this.DescriptionCategorie = descriptionCategorie;
	}
	/**
	 * Contructeur a 3 parametres.
	 * 
	 * @param iDCategorie
	 * @param nom
	 * @param descriptionCategorie
	 */
	public Categorie(int iDCategorie, String nom, String descriptionCategorie) {
		this.IDCategorie = iDCategorie;
		this.Nom = nom;
		this.DescriptionCategorie = descriptionCategorie;
	}
	
	@Override
	public String toString() {
		return Nom;
	}
	/**
	 * Les accesseurs et mutateurs de la classe Categorie se trouvent
	 * ci-dessous, vous y pourrez en acceder a ses valeurs et en modifier si
	 * vous le souhaitez.
	 */
	public int getIDCategorie() {
		return IDCategorie;
	}
	public void setIDCategorie(int iDCategorie) {
		IDCategorie = iDCategorie;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getDescriptionCategorie() {
		return DescriptionCategorie;
	}
	public void setDescriptionCategorie(String descriptionCategorie) {
		DescriptionCategorie = descriptionCategorie;
	}
	

}
