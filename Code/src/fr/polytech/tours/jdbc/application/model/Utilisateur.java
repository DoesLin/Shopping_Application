package fr.polytech.tours.jdbc.application.model;

/**
 * Classe utilisateur qui gere les utilisateurs, qui sont des personnes
 * "especiales" c'est-a-dire que ces personnes peuvent faire des offres pour des
 * annonces ou seulement les consulter.
 * 
 * @author Lin et Moutas Ribeiro
 *
 */
public class Utilisateur extends Personne {
	/**
	 * Attributs
	 */
	/**
	 * IDUtilisateur : de type entier permet d'identifier un utilisateur.
	 */
	private int IDUtilisateur;
	/**
	 * IDPersonne : de type Personne pour pouvoir avoir acces a toutes les
	 * informations personnelles d'un utilisateur
	 */
	private Personne IDPersonne;
	/**
	 * Constructeur par default.
	 */
	public Utilisateur() {
		this(-1, null);
	}
	/**
	 * Constructeur en utilisant l'identifiant d'un utilisateur.
	 * 
	 * @param idUtilisateur
	 */
	public Utilisateur(int idUtilisateur) {
		this(idUtilisateur, null);
	}

	/**
	 * Constructeur en utilisant uniquement l'identifiant de la classe mere,
	 * Personne.
	 * 
	 * @param iDPersonne
	 */
	public Utilisateur(Personne iDPersonne) {
		this.IDPersonne = iDPersonne;
	}
	/**
	 * Constructeur utilisant deux parametres, l'identifiant de personne et
	 * utilisateur.
	 * 
	 * @param iDUtilisateur
	 * @param iDPersonne
	 */
	public Utilisateur(int iDUtilisateur, Personne iDPersonne) {
		this.IDUtilisateur = iDUtilisateur;
		this.IDPersonne = iDPersonne;
	}

	/**
	 * Contructeur permettant de creer un utilisateur avec toutes les donnes le
	 * concernant. Utilisation du contructeur de la classe mere pour creer cet
	 * object.
	 * 
	 * @param idUtilisateur
	 * @param iDPersonne
	 * @param nom
	 * @param prenom
	 * @param adresse
	 */
	public Utilisateur(int idUtilisateur, String iDPersonne, String nom, String prenom, Adresse adresse) {
		super(iDPersonne, nom, prenom, adresse);
		this.IDUtilisateur = idUtilisateur;

	}
	/**
	 * Accesseurs et mutateurs de la classe se trouvent ci-dessous.
	 */
	public int getIDUtilisateur() {
		return IDUtilisateur;
	}

	public void setIDUtilisateur(int iDUtilisateur) {
		IDUtilisateur = iDUtilisateur;
	}

	public Personne getPersonne() {
		return IDPersonne;
	}

	public String getIDPersonne() {
		return IDPersonne.getIDPersonne();
	}

	public void setIDPersonne(Personne iDPersonne) {
		IDPersonne = iDPersonne;
	}

	public int getIDAdresse() {
		return IDPersonne.getAdresseInt();
	}

}
