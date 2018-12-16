package fr.polytech.tours.jdbc.application.model;
/**
 * Classe representant une offre faite par un utilisateur a un vendeur suite a
 * la consultation d'un annonce. Cette offre peut etre accepte, attribut
 * Acceptation a true, ou refuse.
 * 
 * @author Catarina
 *
 */
public class Offre {

	private int IDOffre;
	private Annonce IDAnnonce;
	private Utilisateur IDUtilisateur;
	private boolean Acceptation;

	private String nom;
	private float prix;
	private String util;
	private String adre;
	private String tpTran;
	private String desc;

	public Offre() {
		this(-1, null, null, false);
	}

	public Offre(int iDOffre) {
		this(iDOffre, null, null, false);
	}

	public Offre(int iDOffre, Annonce iDAnnonce, Utilisateur iDUtilisateur) {
		this.IDOffre = iDOffre;
		setIDAnnonce(iDAnnonce);
		// this.IDAnnonce = iDAnnonce;
		setIDUtilisateur(iDUtilisateur);
		// this.IDUtilisateur = iDUtilisateur;
		this.setAcceptation(false);
	}

	public Offre(Annonce iDAnnonce, Utilisateur iDUtilisateur) {
		this.IDOffre = -1;
		setIDAnnonce(iDAnnonce);
		// this.IDAnnonce = iDAnnonce;
		setIDUtilisateur(iDUtilisateur);
		// this.IDUtilisateur = iDUtilisateur;
	}

	public Offre(int iDOffre, Annonce iDAnnonce, Utilisateur iDUtilisateur, boolean Acceptation) {
		this.IDOffre = iDOffre;
		setIDAnnonce(iDAnnonce);
		// this.IDAnnonce = iDAnnonce;
		setIDUtilisateur(iDUtilisateur);
		// this.IDUtilisateur = iDUtilisateur;
		this.setAcceptation(Acceptation);
	}

	public int getIDOffre() {
		return IDOffre;
	}

	public void setIDOffre(int iDOffre) {
		IDOffre = iDOffre;
	}

	public Annonce getIDAnnonce() {
		return IDAnnonce;
	}

	public int getIDAnnonceInt() {
		return IDAnnonce.getIDAnnonce();
	}

	public void setIDAnnonce(Annonce iDAnnonce) {
		IDAnnonce = iDAnnonce;
		if (iDAnnonce != null) {
			nom = iDAnnonce.getNom();
			prix = iDAnnonce.getPrix();
			adre = iDAnnonce.getPosition().toString();
			setTpTran(iDAnnonce.getTypeTransaction());
			desc = iDAnnonce.getDescriptionAnnonce();
		}
	}

	public Utilisateur getIDUtilisateur() {
		return IDUtilisateur;
	}

	public int getIDUtilisateurInt() {
		return IDUtilisateur.getIDUtilisateur();
	}

	public void setIDUtilisateur(Utilisateur iDUtilisateur) {
		IDUtilisateur = iDUtilisateur;
		if (iDUtilisateur != null) {
			util = iDUtilisateur.getPersonne().getPrenom() + iDUtilisateur.getPersonne().getNom();
		}
	}

	public boolean isAcceptation() {
		return Acceptation;
	}

	public void setAcceptation(boolean acceptation) {
		Acceptation = acceptation;
	}

	/**
	 * @return the tpTran
	 */
	public String getTpTran() {
		return tpTran;
	}

	/**
	 * @param tpTran
	 *            the tpTran to set
	 */
	public void setTpTran(String tpTran) {
		this.tpTran = tpTran;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prix
	 */
	public float getPrix() {
		return prix;
	}

	/**
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(float prix) {
		this.prix = prix;
	}

	/**
	 * @return the util
	 */
	public String getUtil() {
		return util;
	}

	/**
	 * @param util
	 *            the util to set
	 */
	public void setUtil(String util) {
		this.util = util;
	}

	/**
	 * @return the posi
	 */
	public String getAdre() {
		return adre;
	}

	/**
	 * @param posi
	 *            the posi to set
	 */
	public void setAdre(String adre) {
		this.adre = adre;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
