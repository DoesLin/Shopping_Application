package fr.polytech.tours.jdbc.application.model;

/**
 * 
 * Classe qui represente les annonces publies dans le site internet.
 * 
 * @author Lin et Moutas Ribeiro
 *
 */
public class Annonce {
	/**
	 * Attributs de la classe Annonce :
	 */
	/**
	 * IDAnnonce : attribut de type int que identifie l'annonce.
	 */
	private int IDAnnonce;

	private String Nom;

	/**
	 * TypeTransaction : attribut de type String qui contient le type de transaction
	 * de l'annonce.
	 */
	private String TypeTransaction;

	/**
	 * Position : de type @see Adresse qui contient la localisation du produit �
	 * vendre dans l'annonce.
	 */
	private Adresse Position;

	/**
	 * Prix : attribut de type float qui decris le prix du produit � vendre.
	 */
	private float Prix;
	/**
	 * DescriptionAnnonce : attribut de type String qui decris le produit mise en
	 * vente dans l'annonce.
	 */
	private String DescriptionAnnonce;
	/**
	 * Categorie : type d'attribut Categorie qui permet de regrouper des produits
	 * dans une categorie.
	 */
	private Categorie Categorie;
	
	private Vendeur Vendeur;

	public Annonce() {
		this.IDAnnonce = -1;
		this.Nom = null;
		this.TypeTransaction = null;
		this.Position = null;
		this.Prix = 0;
		this.DescriptionAnnonce = null;
		this.Categorie = null;
		this.Vendeur = null;
	}

	public Annonce(String Nom, String typeTransaction, Adresse position, float prix,
			String descriptionAnnonce, Categorie categorie, Vendeur vendeur) {
		this.IDAnnonce = -1;
		this.Nom = Nom;
		this.TypeTransaction = typeTransaction;
		this.Position = position;
		this.Prix = prix;
		this.DescriptionAnnonce = descriptionAnnonce;
		this.Categorie = categorie;
		this.Vendeur = vendeur;
	}

	public Annonce(int iDAnnonce, String Nom, String typeTransaction, Adresse position, float prix,
			String descriptionAnnonce, Categorie categorie, Vendeur vendeur) {
		this.IDAnnonce = iDAnnonce;
		this.Nom = Nom;
		this.TypeTransaction = typeTransaction;
		this.Position = position;
		this.Prix = prix;
		this.DescriptionAnnonce = descriptionAnnonce;
		this.Categorie = categorie;
		this.Vendeur = vendeur;
	}

	public Annonce(int iDAnnonce, String Nom, String typeTransaction, float prix, String descriptionAnnonce) {

		this.IDAnnonce = iDAnnonce;
		this.Nom = Nom;
		this.TypeTransaction = typeTransaction;
		this.Prix = prix;
		this.DescriptionAnnonce = descriptionAnnonce;
	}
	
	@Override
	public String toString() {
		return IDAnnonce + Nom + TypeTransaction + Prix + DescriptionAnnonce + Categorie + Position;
	}

	/**
	 * Accesseur de l'attribut IDAttribut.
	 * 
	 * @return IDAttribut.
	 */
	public int getIDAnnonce() {
		return IDAnnonce;
	}

	/**
	 * Mutateur de l'attribut IDAnnonce par le passage en parametre de la nouvelle
	 * valeur.
	 * 
	 * @param iDAnnonce
	 *            de type entier.
	 */
	public void setIDAnnonce(int iDAnnonce) {
		IDAnnonce = iDAnnonce;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return Nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		Nom = nom;
	}

	/**
	 * Accesseur de l'attribut TypeTransaction.
	 * 
	 * @return TypeTransaction de type String.
	 */
	public String getTypeTransaction() {
		return TypeTransaction;
	}

	/**
	 * Mutateur de l'attribut TypeTransaction.
	 * 
	 * @param typeTransaction
	 *            de type String.
	 */
	public void setTypeTransaction(String typeTransaction) {
		TypeTransaction = typeTransaction;
	}

	/**
	 * Accesseur de l'attribut Position.
	 * 
	 * @return Position de type Adresse.
	 */
	public Adresse getPosition() {
		return Position;
	}

	public String getPositionString() {
		String adresse = Integer.toString(Position.getIDAdresse()) + Position.getNRue() + Position.getNomRue()
				+ Position.getCodePostal() + Position.getVille() + Position.getPays();

		return adresse;
	}

	public int getPositionInt() {
		return Position.getIDAdresse();
	}

	/**
	 * Mutateur de l'attribut Position.
	 * 
	 * @param position
	 *            de type Adresse.
	 */
	public void setPosition(Adresse position) {
		Position = position;
	}

	/**
	 * Accesseur de l'attribut Prix.
	 * 
	 * @return Prix
	 */
	public float getPrix() {
		return Prix;
	}

	/**
	 * Mutateur de l'attribut Prix.
	 * 
	 * @param prix
	 */
	public void setPrix(float prix) {
		Prix = prix;
	}

	/**
	 * Accesseur de l'attribut DescriptionAnnonce.
	 * 
	 * @return DescriptionAnnonce
	 */
	public String getDescriptionAnnonce() {
		return DescriptionAnnonce;
	}

	/**
	 * Mutateur de l'attribut DescriptionAnnonce.
	 * 
	 * @param descriptionAnnonce
	 */
	public void setDescriptionAnnonce(String descriptionAnnonce) {
		DescriptionAnnonce = descriptionAnnonce;
	}

	/**
	 * Accesseur de l'attribut Categorie.
	 * 
	 * @return Categorie
	 */
	public Categorie getCategorie() {
		return Categorie;
	}

	public String getCategorieString() {
		String categorie = Integer.toString(Categorie.getIDCategorie()) + Categorie.getNom()
				+ Categorie.getDescriptionCategorie();
		return categorie;
	}

	public int getCategorieInt() {
		return Categorie.getIDCategorie();
	}

	/**
	 * Mutateur de l'attribut Categorie.
	 * 
	 * @param categorie
	 */
	public void setCategorie(Categorie categorie) {
		Categorie = categorie;
	}

	/**
	 * @return the vendeur
	 */
	public int getVendeurInt() {
		return Vendeur.getIDVendeur();
	}

	/**
	 * @return the vendeur
	 */
	public Vendeur getVendeur() {
		return Vendeur;
	}


	/**
	 * @param vendeur the vendeur to set
	 */
	public void setVendeur(Vendeur vendeur) {
		Vendeur = vendeur;
	}
}
