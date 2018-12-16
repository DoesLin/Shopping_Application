package fr.polytech.tours.jdbc.application.model;
/**
 * Classe permettant de gerer les adresses.
 * @author Lin et Moutas Ribeiro
 *
 */
public class Adresse {
	/**
	 * Attributs
	 */
	private int IDAdresse;
	private int NRue;
	private String NomRue;
	private int CodePostal;
	private String Ville;
	private String Pays;
	/**
	 * Constructeur par default.
	 */
	public Adresse(){
		this(-1, -1, "",-1, "", "");
	}
	/**
	 * Contructeur a 6 parametres.
	 * @param iDAdresse
	 * @param nRue
	 * @param nomRue
	 * @param codePostal
	 * @param ville
	 * @param pays
	 */
	public Adresse(int nRue, String nomRue, int codePostal, String ville, String pays) {
		this.IDAdresse = -1;
		this.NRue = nRue;
		this.NomRue = nomRue;
		this.CodePostal = codePostal;
		this.Ville = ville;
		this.Pays = pays;
	}
	/**
	 * Les accesseurs et mutateurs permettant d'acceder aux valeurs des
	 * attributs et/ou les modifier.
	 */
	public Adresse(int iDAdresse, int nRue, String nomRue, int codePostal, String ville, String pays) {
		this.IDAdresse = iDAdresse;
		this.NRue = nRue;
		this.NomRue = nomRue;
		this.CodePostal = codePostal;
		this.Ville = ville;
		this.Pays = pays;
	}
	
	@Override
	public String toString() {
		return NRue + " " + NomRue + " " + CodePostal + " " + Ville + " " + Pays;		
	}
	
	/**
	 * Les accesseurs et mutateurs sont ci dessous
	 */
	public int getIDAdresse() {
		return IDAdresse;
	}
	public void setIDAdresse(int iDAdresse) {
		IDAdresse = iDAdresse;
	}
	public int getNRue() {
		return NRue;
	}
	public void setNRue(int nRue) {
		NRue = nRue;
	}
	public String getNomRue() {
		return NomRue;
	}
	public void setNomRue(String nomRue) {
		NomRue = nomRue;
	}
	public int getCodePostal() {
		return CodePostal;
	}
	public void setCodePostal(int codePostal) {
		CodePostal = codePostal;
	}
	public String getVille() {
		return Ville;
	}
	public void setVille(String ville) {
		Ville = ville;
	}
	public String getPays() {
		return Pays;
	}
	public void setPays(String pays) {
		Pays = pays;
	}
	

}
