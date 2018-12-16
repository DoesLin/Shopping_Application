package fr.polytech.tours.jdbc.application.model;

/**
 * Classe qui permet de gerer les personnes qui consultent les anonces.
 * 
 * @author Lin et Moutas Ribeiro
 *
 */
public class Personne {
	/**
	 * Attributs
	 */
	/**
	 * IDPersonne : attribut de type entier qui permet d'identifier une personne.
	 */
	private String IDPersonne;
	/**
	 * Nom : de type chaine de caracteres, String, qui correspond au nom de la
	 * personne.
	 */

	private String Mot2Passe;
	private String Nom;
	/**
	 * Prenom : represente le prenom de l'utilisateur, attribut de type String.
	 */
	private String Prenom;
	/**
	 * Adresse : correspond a l'adresse de l'utilisateur, attribut de type String
	 */
	private Adresse Adresse;

	public Personne() {
		this("", "", "", "", null);
	}

	public Personne(String iDPersonne, String nom, String prenom, Adresse adresse) {
		this(iDPersonne, nom, prenom);
		this.Adresse = adresse;
	}

	public Personne(String iDPersonne, String nom, String prenom) {
		this.IDPersonne = iDPersonne;
		this.Nom = nom;
		this.Prenom = prenom;
	}

	public Personne(String iDPersonne, String mot2Passe, String nom, String prenom, Adresse adresse) {
		this(iDPersonne, nom, prenom);
		this.Mot2Passe = mot2Passe;
		this.Adresse = adresse;
	}

	public Personne(String iDPersonne, String mot2Passe, String nom, String prenom) {
		this(iDPersonne, nom, prenom);
		this.Mot2Passe = mot2Passe;
	}

	/**
	 * Ci dessous les accesseur et mutateurs
	 */
	public String getIDPersonne() {
		return IDPersonne;
	}

	public void setIDPersonne(String iDPersonne) {
		IDPersonne = iDPersonne;
	}

	/**
	 * @return the mot2Passe
	 */
	public String getMot2Passe() {
		return Mot2Passe;
	}

	/**
	 * @param mot2Passe
	 *            the mot2Passe to set
	 */
	public void setMot2Passe(String mot2Passe) {
		Mot2Passe = mot2Passe;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public Adresse getAdresse() {
		return Adresse;
	}

	public int getAdresseInt() {
		return Adresse.getIDAdresse();
	}

	public void setAdresse(Adresse adresse) {
		Adresse = adresse;
	}

}
